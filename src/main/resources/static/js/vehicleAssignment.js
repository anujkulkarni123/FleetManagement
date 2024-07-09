document.addEventListener('DOMContentLoaded', function() {
    const calendar = document.getElementById('calendar');

    const timeSlotsHTML = Array.from({ length: 17 }, (_, i) => {
        const hour = 8 + i;
        const ampm = hour >= 12 ? 'PM' : 'AM';
        const displayHour = hour % 12 || 12;
        return `
            <div class="time-slot">${displayHour}:00 ${ampm}</div>
            ${Array.from({ length: 7 }, (_, j) => `<div class="time-cell" data-time="${hour}" data-day="${j}"></div>`).join('')}
        `;
    }).join('');

    calendar.insertAdjacentHTML('beforeend', timeSlotsHTML);

    const prevWeekBtn = document.getElementById('prevWeek');
    const nextWeekBtn = document.getElementById('nextWeek');
    const monthSelect = document.getElementById('monthSelect');
    const yearSelect = document.getElementById('yearSelect');

    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    let currentDate = new Date();

    const appointments = assignments.map(assignment => {
        return {
            id: assignment.id,
            title: `${assignment.vehicle ? assignment.vehicle.make + ' ' + assignment.vehicle.model : 'undefined undefined'}`,
            vehicleId: assignment.vehicle ? assignment.vehicle.vehicleId : '',
            driverId: assignment.operator ? assignment.operator.userId : '',
            start: new Date(assignment.startTime),
            end: new Date(assignment.endTime),
            usage: assignment.usage,
            startingOdometer: assignment.startingOdometer,
            endingOdometer: assignment.endingOdometer
        };
    });

    populateMonthSelect();
    populateYearSelect();

    prevWeekBtn.addEventListener('click', () => changeWeek(-1));
    nextWeekBtn.addEventListener('click', () => changeWeek(1));
    monthSelect.addEventListener('change', changeMonthYear);
    yearSelect.addEventListener('change', changeMonthYear);

    updateCalendar();

    function populateMonthSelect() {
        months.forEach((month, index) => {
            const option = document.createElement('option');
            option.value = index;
            option.textContent = month;
            if (index === currentDate.getMonth()) {
                option.selected = true;
            }
            monthSelect.appendChild(option);
        });
    }

    function populateYearSelect() {
        const currentYear = currentDate.getFullYear();
        for (let year = currentYear - 5; year <= currentYear + 5; year++) {
            const option = document.createElement('option');
            option.value = year;
            option.textContent = year;
            if (year === currentYear) {
                option.selected = true;
            }
            yearSelect.appendChild(option);
        }
    }

    function changeWeek(weekOffset) {
        currentDate.setDate(currentDate.getDate() + weekOffset * 7);
        updateCalendar();
    }

    function changeMonthYear() {
        currentDate.setMonth(monthSelect.value);
        currentDate.setFullYear(yearSelect.value);
        updateCalendar();
    }

    function updateCalendar() {
        const startOfWeek = new Date(currentDate);
        startOfWeek.setDate(startOfWeek.getDate() - startOfWeek.getDay() + 1);
        const days = ["monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"];
        days.forEach((day, index) => {
            const dayHeader = document.getElementById(day);
            const date = new Date(startOfWeek);
            date.setDate(startOfWeek.getDate() + index);
            dayHeader.textContent = `${months[date.getMonth()]} ${date.getDate()}`;
        });
        renderAppointments(startOfWeek);
    }

    function renderAppointments(startOfWeek) {
        const timeCells = document.querySelectorAll('.time-cell');
        timeCells.forEach(cell => cell.innerHTML = '');

        const endOfWeek = new Date(startOfWeek);
        endOfWeek.setDate(startOfWeek.getDate() + 6);

        const colors = ['rgba(0, 123, 255, 0.5)', 'rgba(40, 167, 69, 0.5)', 'rgba(255, 193, 7, 0.5)', 'rgba(220, 53, 69, 0.5)', 'rgba(23, 162, 184, 0.5)'];
        let colorIndex = 0;

        appointments.forEach(appointment => {
            const start = new Date(appointment.start.getTime() - (appointment.start.getTimezoneOffset() * 60000)); // Adjust for timezone
            const end = new Date(appointment.end.getTime() - (appointment.end.getTimezoneOffset() * 60000)); // Adjust for timezone

            if (start < endOfWeek && end > startOfWeek) {
                const startDay = start.getDay();
                const endDay = end.getDay();
                const startTime = start.getHours();
                const endTime = end.getHours();

                for (let day = startDay; day <= endDay; day++) {
                    const dayIndex = day === 0 ? 6 : day - 1;

                    const appointmentElement = document.createElement('div');
                    appointmentElement.classList.add('appointment');
                    appointmentElement.style.backgroundColor = colors[colorIndex % colors.length];
                    appointmentElement.style.zIndex = colorIndex;
                    appointmentElement.textContent = appointment.title;
                    appointmentElement.dataset.id = appointment.id;

                    const tooltip = document.createElement('div');
                    tooltip.classList.add('appointment-tooltip');
                    tooltip.textContent = `${appointment.title}\nStart: ${start.toLocaleString()}\nEnd: ${end.toLocaleString()}`;
                    appointmentElement.appendChild(tooltip);

                    let top = 0;
                    let height = 0;

                    if (day === startDay) {
                        top = (startTime - 8) * 50;
                        height = ((day === endDay ? endTime : 24) - startTime) * 50;
                    } else if (day === endDay) {
                        top = 0;
                        height = (endTime - 8) * 50;
                    } else {
                        top = 0;
                        height = 800;
                    }

                    if (top + height > 800) {
                        height = 800 - top;
                    }

                    appointmentElement.style.top = `${top}px`;
                    appointmentElement.style.height = `${height}px`;

                    appointmentElement.addEventListener('click', () => openModal(appointment));

                    const timeCell = document.querySelector(`.time-cell[data-time="${day === startDay ? startTime : 8}"][data-day="${dayIndex}"]`);
                    if (timeCell) {
                        timeCell.appendChild(appointmentElement);
                    }
                }
                colorIndex++;
            }
        });
    }

    function openModal(appointment) {
        document.getElementById('appointmentId').value = appointment.id || '';
        document.getElementById('vehicleSelect').value = appointment.vehicleId || '';
        document.getElementById('operatorSelect').value = appointment.driverId || '';
        document.getElementById('startTime').value = appointment.start.toISOString().slice(0, 16);
        document.getElementById('endTime').value = appointment.end.toISOString().slice(0, 16);
        document.getElementById('usage').value = appointment.usage || '';
        document.getElementById('startingOdometer').value = appointment.startingOdometer || '';
        document.getElementById('endingOdometer').value = appointment.endingOdometer || '';

        const deleteBtn = document.getElementById('deleteAppointmentBtn');
        if (appointment.id) {
            deleteBtn.style.display = 'inline-block';
        } else {
            deleteBtn.style.display = 'none';
        }

        $('#appointmentModal').modal('show');
    }
});

function saveAppointment() {
    const appointmentId = document.getElementById('appointmentId').value;
    const vehicleId = document.getElementById('vehicleSelect').value;
    const driverId = document.getElementById('operatorSelect').value;
    const startTime = document.getElementById('startTime').value;
    const endTime = document.getElementById('endTime').value;
    const usage = document.getElementById('usage').value;
    const startingOdometer = document.getElementById('startingOdometer').value;
    const endingOdometer = document.getElementById('endingOdometer').value;

    const formData = new FormData();
    formData.append('vehicleId', vehicleId);
    formData.append('driverId', driverId);
    formData.append('startDate', startTime);
    formData.append('endDate', endTime);
    formData.append('usage', usage);
    formData.append('startingOdometer', startingOdometer);
    formData.append('endingOdometer', endingOdometer);

    const url = appointmentId ? `/vehicle-assignments/update/${appointmentId}` : '/vehicle-assignments/save';

    fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            clearForm();
            $('#appointmentModal').modal('hide');
            location.reload();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error saving the appointment. Please try again.');
        });
}

function deleteAppointment() {
    const appointmentId = document.getElementById('appointmentId').value;
    if (appointmentId) {
        fetch(`/vehicle-assignments/delete/${appointmentId}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Deleted:', data.message);
                clearForm();
                $('#appointmentModal').modal('hide');
                location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('There was an error deleting the appointment. Please try again.');
            });
    }
}

function clearForm() {
    document.getElementById('appointmentId').value = '';
    document.getElementById('vehicleSelect').value = '';
    document.getElementById('operatorSelect').value = '';
    document.getElementById('startTime').value = '';
    document.getElementById('endTime').value = '';
    document.getElementById('usage').value = '';
    document.getElementById('startingOdometer').value = '';
    document.getElementById('endingOdometer').value = '';
}
