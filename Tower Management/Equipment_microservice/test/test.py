from flask import Flask, jsonify

app = Flask(__name__)

# Mock data
towers = [
    {"tower_id": 400001, "location": "Chennai", "height": 150.5, "type": "4G", "status": "active", "pincode": 600001, "latitude": 13.0827, "longitude": 80.2707, "power_reading": 0, "fuel_reading": 0, "created_at": "2024-08-28 08:36:53.769614", "updated_at": "2024-08-28 08:36:53.769614", "last_maintained": "2024-08-01", "deleted_status": False},
    {"tower_id": 400002, "location": "Bangalore", "height": 200.0, "type": "5G", "status": "active", "pincode": 560001, "latitude": 12.9716, "longitude": 77.5946, "power_reading": 10, "fuel_reading": 5, "created_at": "2024-08-28 08:40:53.769614", "updated_at": "2024-08-28 08:40:53.769614", "last_maintained": "2024-07-15", "deleted_status": False},
    {"tower_id": 400003, "location": "Mumbai", "height": 175.5, "type": "4G", "status": "inactive", "pincode": 400001, "latitude": 19.0760, "longitude": 72.8777, "power_reading": 5, "fuel_reading": 2, "created_at": "2024-08-28 08:45:53.769614", "updated_at": "2024-08-28 08:45:53.769614", "last_maintained": "2024-06-10", "deleted_status": True},
    {"tower_id": 400004, "location": "Delhi", "height": 180.0, "type": "5G", "status": "active", "pincode": 110001, "latitude": 28.7041, "longitude": 77.1025, "power_reading": 20, "fuel_reading": 8, "created_at": "2024-08-28 08:50:53.769614", "updated_at": "2024-08-28 08:50:53.769614", "last_maintained": "2024-05-22", "deleted_status": False},
    {"tower_id": 400005, "location": "Kolkata", "height": 160.0, "type": "4G", "status": "active", "pincode": 700001, "latitude": 22.5726, "longitude": 88.3639, "power_reading": 15, "fuel_reading": 7, "created_at": "2024-08-28 08:55:53.769614", "updated_at": "2024-08-28 08:55:53.769614", "last_maintained": "2024-04-18", "deleted_status": False},
]

# Endpoint to get all towers
@app.route('/api/towers', methods=['GET'])
def get_all_towers():
    return jsonify(towers)

# Endpoint to get a specific tower by id
@app.route('/api/towers/<int:tower_id>', methods=['GET'])
def get_tower_by_id(tower_id):
    tower = next((tower for tower in towers if tower['tower_id'] == tower_id), None)
    if tower:
        return jsonify(tower)
    else:
        return jsonify({"message": "Tower not found"}), 404

if __name__ == '__main__':
    app.run(debug=True,port=5001)
