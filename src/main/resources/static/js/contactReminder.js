document.addEventListener('DOMContentLoaded', () => {
	// Initialize edit and delete buttons
	const editButtons = document.querySelectorAll('.edit-btn');
	const deleteButtons = document.querySelectorAll('.delete-btn');

	let overdueCount = 0;
	let dueSoonCount = 0;

	const currentDate = new Date();
	const dueSoonDate = new Date();
	dueSoonDate.setDate(currentDate.getDate() + 7);

	// Calculate overdue and due soon counts
	reminders.forEach(reminder => {
		const nextDueDateParts = reminder.nextDueDate.split('-');
		const nextDueDate = new Date(nextDueDateParts[0], nextDueDateParts[1] - 1, nextDueDateParts[2]);

		if (nextDueDate < currentDate) {
			overdueCount++;
		} else if (nextDueDate <= dueSoonDate) {
			dueSoonCount++;
		}
	});

	// Update counts in the UI
	document.querySelector('.status-overdue').textContent = overdueCount;
	document.querySelector('.status-due-soon').textContent = dueSoonCount;

	// Edit and delete button event listeners
	editButtons.forEach(button => {
		button.addEventListener('click', event => {
			const reminderId = event.target.closest('tr').getAttribute('data-id');
			showContactReminderModal(reminderId);
		});
	});

	deleteButtons.forEach(button => {
		button.addEventListener('click', event => {
			const reminderId = event.target.closest('tr').getAttribute('data-id');
			deleteContactReminder(reminderId);
		});
	});

	// Search functionality
	const searchInput = document.getElementById('searchInput');
	searchInput.addEventListener('input', () => {
		const searchValue = searchInput.value.toLowerCase();
		const tableRows = document.querySelectorAll('#contactRemindersTable tbody tr');

		tableRows.forEach(row => {
			const renewalTypeText = row.querySelector('.renewalType-column').textContent.toLowerCase();
			if (renewalTypeText.includes(searchValue)) {
				row.style.display = '';
			} else {
				row.style.display = 'none';
			}
		});
	});
});

document.addEventListener('DOMContentLoaded', () => {
    let selectedTeamIds = new Set();

    // Tippy.js popover initialization
    const popoverButtons = document.querySelectorAll('[data-content-id]');

    popoverButtons.forEach(button => {
        const contentId = button.getAttribute('data-content-id');
        const contentElement = document.getElementById(contentId);

        if (contentElement) {
            tippy(button, {
                content: contentElement.innerHTML,
                allowHTML: true,
                interactive: true,
                placement: 'bottom',
                theme: 'light',
                onShow(instance) {
                    popoverButtons.forEach(otherButton => {
                        if (otherButton !== button) {
                            const otherInstance = otherButton._tippy;
                            if (otherInstance) {
                                otherInstance.hide();
                            }
                        }
                    });
                },
            });
        } else {
            console.error(`Element with ID ${contentId} not found.`);
        }
    });

    // Close Tippy popovers when clicking outside
    document.addEventListener('click', (e) => {
        const target = e.target;
        const isPopoverButton = target.matches('[data-content-id]');
        const isPopoverContent = target.closest('.tippy-box');

        if (!isPopoverButton && !isPopoverContent) {
            popoverButtons.forEach(button => {
                const instance = button._tippy;
                if (instance) {
                    instance.hide();
                }
            });
        }
    });

    // Event delegation for checkbox changes
    document.addEventListener('change', (event) => {
        if (event.target.classList.contains('team-checkbox')) {
            console.log('Team checkbox triggered');
            const teamId = event.target.value;
            if (event.target.checked) {
                selectedTeamIds.add(teamId);
            } else {
                selectedTeamIds.delete(teamId);
            }
            console.log('Selected team IDs:', Array.from(selectedTeamIds));
        }
    });

    // Apply button action
    window.applySelection = () => {
        const uniqueTeams = Array.from(selectedTeamIds).sort();
        const selectedItems = {
            teams: uniqueTeams
        };
        console.log('Selected items:', selectedItems);
        filterItems(selectedItems);
    };

    // Cancel button action
    window.cancelSelection = () => {
        selectedTeamIds.clear();
        document.querySelectorAll('.team-checkbox').forEach(checkbox => {
            checkbox.checked = false;
        });
        console.log('Selection cleared');
    };
});

const filterItems = (selectedItems) => {
    fetch('/contact-reminders/filter', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedItems)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Filtered Contact Reminders:', data);
        updateRemindersUI(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
};

function updateRemindersUI(reminders) {
    const tbody = document.querySelector('#contactRemindersTable tbody');
    tbody.innerHTML = ''; // Clear the current table body content

    reminders.forEach(reminder => {
        const row = document.createElement('tr');
        row.dataset.id = reminder.id;

        row.innerHTML = `
            <td>${reminder.renewalType}</td>
            <td>${reminder.dueDate}</td>
            <td>${reminder.watchers}</td>
            <td>${reminder.status}</td>
            <td>${reminder.nextDueDate}</td>
            <td>
                <button class="btn btn-primary btn-sm edit-btn">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-danger btn-sm delete-btn" data-id="${reminder.id}">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        `;

        tbody.appendChild(row);
    });
}

function showContactReminderModal(reminderId) {
	fetch(`/contact-reminders/${reminderId}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(reminder => {
			console.log('Fetched reminder:', reminder);

			document.getElementById('contactReminderId').value = reminder.id || '';
			document.getElementById('teamId').value = reminder.team ? reminder.team.id : 1;
			document.getElementById('renewalType').value = reminder.renewalType || '';
			document.getElementById('dueDate').value = reminder.dueDate || '';
			document.getElementById('status').value = reminder.status || '';
			document.getElementById('nextDueDate').value = reminder.nextDueDate || '';
			document.getElementById('watchersSelect').value = reminder.watchers ? reminder.watchers : '';

			$('#contactReminderModal').modal('show'); // Ensure this line is correct
		})
		.catch(error => console.error('Error fetching reminder:', error));
}

function clearContactReminderForm() {
	document.getElementById('contactReminderId').value = '';
	document.getElementById('teamId').value = '';
	document.getElementById('renewalType').value = '';
	document.getElementById('dueDate').value = '';
	document.getElementById('status').value = '';
	document.getElementById('nextDueDate').value = '';
	document.getElementById('watchersSelect').value = '';

	$('#contactReminderModal').modal('hide'); // Ensure this line is correct
}


function saveContactReminder() {
    const contactReminderId = document.getElementById('contactReminderId').value;
    const renewalType = document.getElementById('renewalType').value;
    const dueDate = document.getElementById('dueDate').value;
    const status = document.getElementById('status').value;
    const nextDueDate = document.getElementById('nextDueDate').value;
    const watchersSelect = document.getElementById('watchersSelect').value;

    // Create FormData and append values
    const formData = new FormData();
    formData.append('id', contactReminderId || '');
    formData.append('teamId', 1);
    formData.append('renewalType', renewalType || '');
    formData.append('dueDate', dueDate || '');
    formData.append('status', status || '');
    formData.append('nextDueDate', nextDueDate || '');
    formData.append('watcherId', watchersSelect || '');

    // Log FormData content
    for (let pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    const url = contactReminderId ? `/contact-reminders/update/${contactReminderId}` : '/contact-reminders/save';

    fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
            'Accept': 'application/json'
        }
    })
    .then(async response => {
        if (!response.ok) {
            const text = await response.text();
            throw new Error(text);
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data);
        clearContactReminderForm();
        $('#contactReminderModal').modal('hide');
        location.reload();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('There was an error saving the contact reminder. Please try again.');
    });
}

function deleteContactReminder(reminderId) {
	console.log(reminderId);

	fetch(`/contact-reminders/delete/${reminderId}`, {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json'
		}
	})
		.then(async response => {
			if (!response.ok) {
				const text = await response.text();
				throw new Error(text);
			}
			return response;
		})
		.then(response => {
			console.log('Success: Contact reminder deleted');
			location.reload();
		})
		.catch(error => {
			console.error('Error deleting reminder:', error);
			alert('There was an error deleting the contact reminder. Please try again.');
		});
}

