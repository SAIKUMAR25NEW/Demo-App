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
        .catch(error => console.error("Error:", error));
}
