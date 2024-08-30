from flask import Flask, jsonify

app = Flask(__name__)

# Sample data, simulating database records
work_orders = [
    {"workorder_id": 200, "tower_id": 400001, "user_id": 500001, "task_description": "New task description", "scheduled_date": "2024-08-29", "status": "pending", "completed_date": "", "created_at": "2024-08-29 12:50:06.150607", "deleted_status": "f", "updated_at": "2024-08-29 12:50:39.719445"},
    {"workorder_id": 221, "tower_id": 400001, "user_id": 500001, "task_description": "Inspect antenna alignment", "scheduled_date": "2024-09-01", "status": "pending", "completed_date": "", "created_at": "2024-08-29 15:21:18.614013", "deleted_status": "f", "updated_at": "2024-08-29 15:21:18.614013"},
    {"workorder_id": 222, "tower_id": 400011, "user_id": 500002, "task_description": "Replace faulty cables", "scheduled_date": "2024-09-02", "status": "completed", "completed_date": "", "created_at": "2024-08-29 15:21:18.614013", "deleted_status": "f", "updated_at": "2024-08-29 15:21:18.614013"},
    # Add more records as needed
]

@app.route('/workorder/<int:workorder_id>', methods=['GET'])
def get_workorder(workorder_id):
    # Find the work order with the given workorder_id
    workorder = next((wo for wo in work_orders if wo['workorder_id'] == workorder_id), None)
    if workorder is None:
        return jsonify({"error": "Work order not found"}), 404
    return jsonify(workorder)

if __name__ == '__main__':
    app.run(debug=True, port=5002)
