from flask import Flask, jsonify, request

app = Flask(__name__)

# Example data
towers = [
    {
        "tower_id": "400001",
        "location": "Chennai",
        "height": 150.5,
        "type": "4G",
        "status": "active",
        "pincode": "600001",
        "latitude": 13.0827,
        "longitude": 80.2707,
        "power_reading": 0,
        "fuel_reading": 0,
        "created_at": "2024-08-28T08:36:53.769614",
        "updated_at": "2024-08-28T08:36:53.769614",
        "last_maintained": "2024-08-01",
        "deleted_status": "f"
    },
    {
        "tower_id": "400002",
        "location": "Chennai",
        "height": 150.5,
        "type": "4G",
        "status": "active",
        "pincode": "600001",
        "latitude": 13.0827,
        "longitude": 80.2707,
        "power_reading": 0,
        "fuel_reading": 0,
        "created_at": "2024-08-28T08:36:53.769614",
        "updated_at": "2024-08-28T08:36:53.769614",
        "last_maintained": "2024-08-01",
        "deleted_status": "f"
    }
    # Add more rows as needed
]

@app.route('/api/towers', methods=['GET'])
def get_towers():
    tower_id = request.args.get('tower_id')
    if tower_id:
        # Search for the tower with the given ID
        tower = next((t for t in towers if t['tower_id'] == tower_id), None)
        if tower:
            return jsonify(tower)
        else:
            return jsonify({"error": "Tower not found"}), 404
    else:
        # Return all towers if no ID is specified
        return jsonify(towers)

if __name__ == '__main__':
    app.run(debug=True,port=5000)
