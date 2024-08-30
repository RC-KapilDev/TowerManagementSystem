from flask import Flask, jsonify, request

app = Flask(__name__)

# Sample data, simulating database records
towers = [
    {"tower_id": 400003, "location": "Chennai", "height": 150.5, "type": "4G", "status": "active", "pincode": 600001, "latitude": 13.0827, "longitude": 80.2707, "power_reading": 0, "fuel_reading": 0, "created_at": "2024-08-29 12:44:27.489862", "updated_at": "2024-08-29 12:44:27.489862", "last_maintained": "2024-08-01", "deleted_status": "f"},
    {"tower_id": 400004, "location": "Delhi", "height": 120, "type": "5G", "status": "active", "pincode": 110001, "latitude": 28.6139, "longitude": 77.209, "power_reading": 0, "fuel_reading": 0, "created_at": "2024-08-29 15:17:54.522464", "updated_at": "2024-08-29 15:17:54.522464", "last_maintained": "2024-07-15", "deleted_status": "f"},
    # Add more records as needed
]

@app.route('/tower/<int:tower_id>', methods=['GET'])
def get_tower(tower_id):
    # Find the tower with the given tower_id
    tower = next((t for t in towers if t['tower_id'] == tower_id), None)
    if tower is None:
        return jsonify({"error": "Tower not found"}), 404
    return jsonify(tower)

if __name__ == '__main__':
    app.run(debug=True, port=5001)
