// Function to fetch and display tower data
function loadTowers() {
    fetch('http://localhost:8083/api/towers')
        .then(response => response.json())
        .then(data => {
            let towerTableBody = document.getElementById('tower-table-body');
            towerTableBody.innerHTML = '';
            data.forEach(tower => {
                let row = `<tr>
                    <td>${tower.tower_id}</td>
                    <td>${tower.height}</td>
                    <td>${tower.type}</td>
                    <td>${tower.status}</td>
                    <td>${tower.location}</td>
                    <td>${tower.pincode}</td>
                    <td>
                        <button class="btn btn-success btn-sm" onclick="editTower(${tower.tower_id})">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteTower(${tower.tower_id})">Delete</button>
                    </td>
                </tr>`;
                towerTableBody.innerHTML += row;
            });
        });
}

// Function to handle adding a new tower
document.getElementById('addTowerForm').addEventListener('submit', function (e) {
    e.preventDefault();
    let newTower = {
        height: document.getElementById('height').value,
        type: document.getElementById('type').value,
        status: document.getElementById('status').value,
        location: document.getElementById('location').value,
        pincode: document.getElementById('pincode').value,
        latitude: document.getElementById('latitude').value,
        longitude: document.getElementById('longitude').value,
        power_reading: document.getElementById('power_reading').value,
        fuel_reading: document.getElementById('fuel_reading').value,
        deletedStatus: false
    };

    fetch('http://localhost:8083/api/towers', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newTower)
    }).then(response => {
        if (response.ok) {
            loadTowers();
            $('#addTowerModal').modal('hide');
        } else {
            alert('Failed to add tower');
        }
    });
});

// Function to handle editing a tower
function editTower(towerId) {
    // Fetch the tower data and prefill the form (you can add a similar modal for editing)
    // Example PUT request (similar to the add request)
    let updatedTower = {
        height: 140.5,
        type: "LTE",
        status: "under-maintenance",
        location: "Bangaloreee",
        pincode: 560001,
        latitude: 12.9716,
        longitude: 77.5946,
        power_reading: 1150,
        fuel_reading: 275.30
    };

    fetch(`http://localhost:8083/api/towers/${towerId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTower)
    }).then(response => {
        if (response.ok) {
            loadTowers();
        } else {
            alert('Failed to update tower');
        }
    });
}

// Function to handle deleting a tower
function deleteTower(towerId) {
    fetch(`http://localhost:8083/api/towers/${towerId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            loadTowers();
        } else {
            alert('Failed to delete tower');
        }
    });
}

// Initial load of tower data
loadTowers();
