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
			showVehicleReminderModal(reminderId);
		});
	});

	deleteButtons.forEach(button => {
		button.addEventListener('click', event => {
			const reminderId = event.target.closest('tr').getAttribute('data-id');
			deleteVehicleReminder(reminderId);
		});
	});

	// Search functionality
	const searchInput = document.getElementById('searchInput');
	searchInput.addEventListener('input', () => {
		const searchValue = searchInput.value.toLowerCase();
		const tableRows = document.querySelectorAll('#vehicleRemindersTable tbody tr');

		tableRows.forEach(row => {
			const vehicleText = row.querySelector('.vehicle-column').textContent.toLowerCase();
			if (vehicleText.includes(searchValue)) {
				row.style.display = '';
			} else {
				row.style.display = 'none';
			}
		});
	});
});

document.addEventListener('DOMContentLoaded', () => {
    let selectedVehicleIds = new Set();

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
        if (event.target.classList.contains('vehicle-checkbox')) {
            console.log('Vehicle checkbox triggered');
            const vehicleId = event.target.value;
            if (event.target.checked) {
                selectedVehicleIds.add(vehicleId);
            } else {
                selectedVehicleIds.delete(vehicleId);
            }
            console.log('Selected vehicle IDs:', Array.from(selectedVehicleIds));
        }
    });

    // Apply button action
    window.applySelection = () => {
        const uniqueVehicles = Array.from(selectedVehicleIds).sort();
        const selectedItems = {
            vehicles: uniqueVehicles
        };
        console.log('Selected items:', selectedItems);
        filterItems(selectedItems);
    };

    // Cancel button action
    window.cancelSelection = () => {
        selectedVehicleIds.clear();
        document.querySelectorAll('.vehicle-checkbox').forEach(checkbox => {
            checkbox.checked = false;
        });
        console.log('Selection cleared');
    };
});

const filterItems = (selectedItems) => {
    fetch('/vehicle-reminders/filter', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(selectedItems)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Filtered Vehicle Reminders:', data);
        updateRemindersUI(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
};

function updateRemindersUI(reminders) {
    const tbody = document.querySelector('#vehicleRemindersTable tbody');
    tbody.innerHTML = ''; // Clear the current table body content

    reminders.forEach(reminder => {
        const row = document.createElement('tr');
        row.dataset.id = reminder.id;

        row.innerHTML = `
            <td>${reminder.vehicle.make} ${reminder.vehicle.model} - ${reminder.vehicle.year}</td>
            <td>${reminder.renewalType}</td>
            <td>${reminder.watchers.firstName} ${reminder.watchers.lastName}</td>
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

function showVehicleReminderModal(reminderId) {
	fetch(`/vehicle-reminders/${reminderId}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(reminder => {
			console.log('Fetched reminder:', reminder);

			document.getElementById('vehicleReminderId').value = reminder.id || '';
			document.getElementById('teamId').value = reminder.team ? reminder.team.id : 1;
			document.getElementById('vehicleSelect').value = reminder.vehicle.vehicleId ? reminder.vehicle.vehicleId : reminder.vehicle;
			document.getElementById('renewalType').value = reminder.renewalType || '';
			document.getElementById('status').value = reminder.status || '';
			document.getElementById('nextDueDate').value = reminder.nextDueDate || '';
			document.getElementById('watchersSelect').value = reminder.watchers.userId ? reminder.watchers.userId : reminder.watchers;

			$('#vehicleReminderModal').modal('show');
		})
		.catch(error => console.error('Error fetching reminder:', error));
}

function saveVehicleReminder() {
    const vehicleReminderId = document.getElementById('vehicleReminderId').value;
    const vehicleId = document.getElementById('vehicleSelect').value;
    const renewalType = document.getElementById('renewalType').value;
    const status = document.getElementById('status').value;
    const nextDueDate = document.getElementById('nextDueDate').value;
    const watchersSelect = document.getElementById('watchersSelect').value;

    /*// Logging values to ensure they are being retrieved correctly
    console.log('Vehicle Reminder ID:', vehicleReminderId);
    console.log('Team ID:', teamId);
    console.log('Vehicle ID:', vehicleId);
    console.log('Renewal Type:', renewalType);
    console.log('Status:', status);
    console.log('Next Due Date:', nextDueDate);
    console.log('Watcher ID:', watchersSelect);*/

    // Create FormData and append values
    const formData = new FormData();
    formData.append('id', vehicleReminderId || '');
    formData.append('teamId', 1);
    formData.append('vehicleId', vehicleId || '');
    formData.append('renewalType', renewalType || '');
    formData.append('status', status || '');
    formData.append('nextDueDate', nextDueDate || '');
    formData.append('watcherId', watchersSelect || '');

   	 // Log FormData content
    for (let pair of formData.entries()) {
        console.log(pair[0] + ': ' + pair[1]);
    }

    const url = vehicleReminderId ? `/vehicle-reminders/update/${vehicleReminderId}` : '/vehicle-reminders/save';

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
        clearVehicleReminderForm();
        $('#vehicleReminderModal').modal('hide');
        location.reload();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('There was an error saving the vehicle reminder. Please try again.');
    });
}

function deleteVehicleReminder(reminderId) {
	console.log(reminderId);

	fetch(`/vehicle-reminders/delete/${reminderId}`, {
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
			console.log('Success: Vehicle reminder deleted');
			location.reload();
		})
		.catch(error => {
			console.error('Error deleting reminder:', error);
			alert('There was an error deleting the vehicle reminder. Please try again.');
		});
}

function clearVehicleReminderForm() {
	document.getElementById('vehicleReminderId').value = '';
	document.getElementById('teamId').value = '';
	document.getElementById('vehicleSelect').value = '';
	document.getElementById('renewalType').value = '';
	document.getElementById('status').value = '';
	document.getElementById('nextDueDate').value = '';
	document.getElementById('watchersSelect').value = '';

	$('#vehicleReminderModal').modal('hide');
}
