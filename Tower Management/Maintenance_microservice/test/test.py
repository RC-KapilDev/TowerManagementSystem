from flask import Flask, jsonify

app = Flask(__name__)

# Sample user data
users = [
    {
        "user_id": 500001,
        "name": "John Doe",
        "email": "new.email@example.com",
        "specialisation": "Electrical Engineering",
        "location": "Chennai",
        "pincode": 600001,
        "role": "Field Technician",
        "active_status": True,
        "deleted_status": False,
        "username": "johnf4c",
        "created_at": "2024-08-29 12:31:26.182184",
        "password": "john3724",
        "updated_at": "2024-08-29 12:31:52.023769"
    },
    {
        "user_id": 500002,
        "name": "Kapil Dev",
        "email": "kapildev@example.com",
        "specialisation": "Electrical Engineering",
        "location": "Chennai",
        "pincode": 600042,
        "role": "Field Technician",
        "active_status": True,
        "deleted_status": False,
        "username": "kapia86",
        "created_at": "2024-08-29 14:46:10.298533",
        "password": "kapi481e",
        "updated_at": "2024-08-29 14:46:10.298533"
    },
    # Add more users here...
]

@app.route('/users/<int:user_id>', methods=['GET'])
def get_user_by_id(user_id):
    user = next((user for user in users if user["user_id"] == user_id), None)
    if user:
        return jsonify(user), 200
    else:
        return jsonify({"error": "User not found"}), 404

if __name__ == '__main__':
    app.run(debug=True)
