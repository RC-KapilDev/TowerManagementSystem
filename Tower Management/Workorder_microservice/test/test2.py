from flask import Flask, jsonify, request

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
    # Add more user records here as needed
]

# Endpoint to retrieve all users
@app.route('/api/users', methods=['GET'])
def get_users():
    return jsonify(users)

# Endpoint to retrieve a user by ID
@app.route('/api/users/<int:user_id>', methods=['GET'])
def get_user(user_id):
    user = next((user for user in users if user["user_id"] == user_id), None)
    if user is not None:
        return jsonify(user)
    else:
        return jsonify({"error": "User not found"}), 404

# Endpoint to add a new user
@app.route('/api/users', methods=['POST'])
def add_user():
    new_user = request.json
    users.append(new_user)
    return jsonify(new_user), 201

# Endpoint to check if user exists by ID
@app.route('/api/users/exists/<int:user_id>', methods=['GET'])
def user_exists(user_id):
    exists = any(user["user_id"] == user_id for user in users)
    return jsonify({"exists": exists})

if __name__ == '__main__':
    app.run(debug=True,port=5001)
