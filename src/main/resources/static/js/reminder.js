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
			showServiceReminderModal(reminderId);
		});
	});

	deleteButtons.forEach(button => {
		button.addEventListener('click', event => {
			const reminderId = event.target.closest('tr').getAttribute('data-id');
			deleteServiceReminder(reminderId);
		});
	});

	// Search functionality
	const searchInput = document.getElementById('searchInput');
	searchInput.addEventListener('input', () => {
		const searchValue = searchInput.value.toLowerCase();
		const tableRows = document.querySelectorAll('#serviceRemindersTable tbody tr');

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

    // Event delegation for checkbox changes
    document.addEventListener('change', (event) => {
        if (event.target.classList.contains('vehicle-checkbox')) {
            const vehicleId = event.target.value;
            if (event.target.checked) {
                selectedVehicleIds.add(vehicleId);
            } else {
                selectedVehicleIds.delete(vehicleId);
            }
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

    // Apply button action
    window.applySelection = () => {
        const uniqueVehicles = Array.from(selectedVehicleIds);
        console.log('Selected vehicle IDs:', uniqueVehicles.sort());
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



function showServiceReminderModal(reminderId) {
	fetch(`/service-reminders/${reminderId}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(reminder => {
			console.log('Fetched reminder:', reminder);

			document.getElementById('serviceReminderId').value = reminder.id || '';
			document.getElementById('teamId').value = reminder.team ? reminder.team.id : 1;
			document.getElementById('vehicleSelect').value = reminder.vehicle ? reminder.vehicle.id : '';
			document.getElementById('status').value = reminder.status || '';
			document.getElementById('nextDueDate').value = reminder.nextDueDate || '';
			document.getElementById('serviceTaskSelect').value = reminder.serviceTask ? reminder.serviceTask.id : '';
			document.getElementById('activeWorkOrder').value = reminder.activeWorkOrder || '';
			document.getElementById('lastCompletedDate').value = reminder.lastCompletedDate || '';
			document.getElementById('compliance').value = reminder.compliance || '';
			document.getElementById('watchersSelect').value = reminder.watchers ? reminder.watchers.id : '';

			$('#serviceReminderModal').modal('show');
		})
		.catch(error => console.error('Error fetching reminder:', error));
}

function saveServiceReminder() {
	const serviceReminderId = document.getElementById('serviceReminderId').value;
	const teamId = document.getElementById('teamId').value;
	const vehicleId = document.getElementById('vehicleSelect').value;
	const status = document.getElementById('status').value;
	const nextDueDate = document.getElementById('nextDueDate').value;
	const serviceTaskId = document.getElementById('serviceTaskSelect').value;
	const activeWorkOrder = document.getElementById('activeWorkOrder').value;
	const lastCompletedDate = document.getElementById('lastCompletedDate').value;
	const compliance = document.getElementById('compliance').value;
	const watchersSelect = document.getElementById('watchersSelect').value;

	const formData = new FormData();
	formData.append('id', serviceReminderId);
	formData.append('teamId', teamId);
	formData.append('vehicleId', vehicleId);
	formData.append('status', status);
	formData.append('nextDueDate', nextDueDate);
	formData.append('serviceTaskId', serviceTaskId);
	formData.append('activeWorkOrder', activeWorkOrder);
	formData.append('lastCompletedDate', lastCompletedDate);
	formData.append('compliance', compliance);
	formData.append('watcherId', watchersSelect);

	const url = serviceReminderId ? `/service-reminders/update/${serviceReminderId}` : '/service-reminders/save';

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
			clearServiceReminderForm();
			$('#serviceReminderModal').modal('hide');
			location.reload();
		})
		.catch(error => {
			console.error('Error:', error);
			alert('There was an error saving the service reminder. Please try again.');
		});
}

function deleteServiceReminder(reminderId) {
	console.log(reminderId);

	fetch(`/service-reminders/delete/${reminderId}`, {
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
			console.log('Success: Service reminder deleted');
			location.reload();
		})
		.catch(error => {
			console.error('Error deleting reminder:', error);
			alert('There was an error deleting the service reminder. Please try again.');
		});
}

function clearServiceReminderForm() {
	document.getElementById('serviceReminderId').value = '';
	document.getElementById('teamId').value = '';
	document.getElementById('vehicleSelect').value = '';
	document.getElementById('status').value = '';
	document.getElementById('nextDueDate').value = '';
	document.getElementById('serviceTaskSelect').value = '';
	document.getElementById('activeWorkOrder').value = '';
	document.getElementById('lastCompletedDate').value = '';
	document.getElementById('compliance').value = '';
	document.getElementById('watchersSelect').value = '';

	$('#serviceReminderModal').modal('hide');
}

function toggleCheckbox(element) {
	console.log('checked');
	element.classList.toggle('checked');
}

function cancelSelection() {
	const checkboxes = document.querySelectorAll('.checkbox-container');
	checkboxes.forEach(checkbox => checkbox.classList.remove('checked'));
}

function applySelection() {
	const selectedVehicles = [];
	const checkboxes = document.querySelectorAll('.checkbox-container.checked');
	checkboxes.forEach(checkbox => {
		const vehicleId = checkbox.getAttribute('data-vehicle-id');
		selectedVehicles.push(vehicleId);
	});
	console.log('Selected vehicles:', selectedVehicles);
	// Handle the selected vehicles as needed
}