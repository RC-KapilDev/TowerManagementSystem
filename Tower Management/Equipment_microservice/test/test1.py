from flask import Flask, jsonify, request
from datetime import datetime

app = Flask(__name__)

# Sample data
workorders = [
    {
        "workorder_id": 1,
        "description": "Fix tower base",
        "status": "pending",
        "assigned_to": "John Doe",
        "priority": "high",
        "created_at": "2024-08-28T08:36:53",
        "updated_at": "2024-08-28T08:36:53",
        "completed_at": None
    },
    {
        "workorder_id": 2,
        "description": "Inspect antennas",
        "status": "completed",
        "assigned_to": "Jane Smith",
        "priority": "medium",
        "created_at": "2024-08-27T10:30:00",
        "updated_at": "2024-08-28T09:00:00",
        "completed_at": "2024-08-28T09:00:00"
    }
]

@app.route('/api/workorders/<int:workorder_id>', methods=['GET'])
def get_workorder(workorder_id):
    workorder = next((wo for wo in workorders if wo['workorder_id'] == workorder_id), None)
    if workorder:
        return jsonify(workorder)
    else:
        return jsonify({'error': 'Workorder not found'}), 404

@app.route('/api/workorders', methods=['GET'])
def get_workorders_by_status():
    status = request.args.get('status')
    if status:
        filtered_workorders = [wo for wo in workorders if wo['status'] == status]
        return jsonify(filtered_workorders)
    else:
        return jsonify(workorders)

if __name__ == '__main__':
    app.run(port=5000, debug=True)
