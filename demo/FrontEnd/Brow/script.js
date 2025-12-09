// 1. GET Employees
function fetchEmployees() {
    fetch("http://localhost:8080/employees")
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
        .catch(error => console.error("Error fetching employees:", error));
}


// 2. POST Add Employee
function addEmployee(event) {
    event.preventDefault();

    const employee = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        salary: document.getElementById("salary").value
    };

    fetch("http://localhost:8080/employees", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("admin:password123")
        },
        body: JSON.stringify(employee)
    })
    .then(response => {
        if (!response.ok) {
            console.log("STATUS:", response.status);
            alert("Error: " + response.status + " — Check console");
            throw new Error("Request failed");
        }
        return response.json();
    })
    .then(data => {
        alert("Employee added successfully!");
        fetchEmployees();
        document.getElementById("employeeForm").reset();
    })
    .catch(error => console.error("Add employee failed:", error));
}
// 3. PUT
function updateEmployee(event) {
    event.preventDefault();

    const id = document.getElementById("updateId").value;

    const updatedEmployee = {
        name: document.getElementById("updateName").value,
        email: document.getElementById("updateEmail").value,
        salary: document.getElementById("updateSalary").value
    };

    fetch(`http://localhost:8080/employees/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("admin:password123")
        },
        body: JSON.stringify(updatedEmployee)
    })
    .then(response => {
        if (!response.ok) {
            alert("Update failed. Check ID.");
            throw new Error("Update failed");
        }
        return response.json();
    })
    .then(data => {
        alert("Employee updated successfully.");
        fetchEmployees();
        document.getElementById("updateForm").reset();
    })
    .catch(error => console.error("Update error:", error));
}
// 4. DELETE
function deleteEmployee(event) {
    event.preventDefault();

    const id = document.getElementById("deleteId").value;

    fetch(`http://localhost:8080/employees/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Basic " + btoa("admin:password123")
        }
    })
    .then(response => {
        if (!response.ok) {
            alert("Delete failed. Check ID.");
            throw new Error("Delete error");
        }
        return response.text();
    })
    .then(msg => {
        alert("Employee deleted.");
        fetchEmployees();
        document.getElementById("deleteForm").reset();
    })
    .catch(error => console.error("Delete error:", error));
}

