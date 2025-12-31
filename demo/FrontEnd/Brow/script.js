const apiUrl = "http://localhost:8080/employees";

// Show message helper
function showMessage(message, type = "info") {
    const msgBox = document.getElementById("message");
    msgBox.innerText = message;
    msgBox.className = type;  // success, error, info
    msgBox.style.display = "block";
    setTimeout(() => msgBox.style.display = "none", 4000);
}

// ======================= GET =======================
function fetchEmployees() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#employeeTable tbody");
            tableBody.innerHTML = "";

            data.forEach(emp => {
                const row = `
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>${emp.email}</td>
                        <td>${emp.salary}</td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        })
        .catch(() => showMessage("Failed to load employees. Server unavailable.", "error"));
}

// ======================= POST =======================
function addEmployee(event) {
    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const salary = document.getElementById("salary").value.trim();

    if (!name || !email || !salary) {
        showMessage("All fields are required!", "error");
        return;
    }

    if (!email.includes("@")) {
        showMessage("Invalid email format!", "error");
        return;
    }

    const employee = { name, email, salary };

    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("admin:password123")
        },
        body: JSON.stringify(employee)
    })
        .then(response => {
            if (!response.ok) throw new Error("Error");
            return response.json();
        })
        .then(() => {
            showMessage("Employee added successfully!", "success");
            fetchEmployees();
            document.getElementById("employeeForm").reset();
        })
        .catch(() => showMessage("Failed to add employee.", "error"));
}


// ======================= PUT =======================
function updateEmployee(event) {
    event.preventDefault();

    const id = document.getElementById("updateId").value.trim();
    const name = document.getElementById("updateName").value.trim();
    const email = document.getElementById("updateEmail").value.trim();
    const salary = document.getElementById("updateSalary").value.trim();

    if (!id) {
        showMessage("Employee ID is required!", "error");
        return;
    }

    const employee = { name, email, salary };

    fetch(`${apiUrl}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("admin:password123")
        },
        body: JSON.stringify(employee)
    })
        .then(response => {
            if (!response.ok) throw new Error();
            return response.json();
        })
        .then(() => {
            showMessage("Employee updated successfully!", "success");
            fetchEmployees();
            document.getElementById("updateForm").reset();
        })
        .catch(() => showMessage("Failed to update employee.", "error"));
}


// ======================= DELETE =======================
function deleteEmployee(event) {
    event.preventDefault();

    const id = document.getElementById("deleteId").value.trim();

    if (!id) {
        showMessage("Employee ID is required!", "error");
        return;
    }

    fetch(`${apiUrl}/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Basic " + btoa("admin:password123")
        }
    })
        .then(response => {
            if (!response.ok) throw new Error();
            return response.text();
        })
        .then(() => {
            showMessage("Employee deleted!", "success");
            fetchEmployees();
            document.getElementById("deleteForm").reset();
        })
        .catch(() => showMessage("Failed to delete employee.", "error"));
}
