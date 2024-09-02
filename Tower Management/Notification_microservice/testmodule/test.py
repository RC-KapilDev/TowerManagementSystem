from flask import Flask, jsonify, request
import psycopg2
import json

app = Flask(__name__)

# Database connection parameters
DB_HOST = 'localhost'
DB_PORT = '5432'
DB_NAME = 'test1'
DB_USER = 'postgres'
DB_PASSWORD = 'root'

def fetch_user_details(user_id=None):
    # Connect to the PostgreSQL database
    conn = psycopg2.connect(
        host=DB_HOST,
        port=DB_PORT,
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD
    )
    cursor = conn.cursor()

    # Query to fetch user details
    query = "SELECT * FROM users"
    params = []

    if user_id:
        query += " WHERE user_id = %s"
        params.append(user_id)

    # Execute the query
    cursor.execute(query, params)

    # Fetch all rows from the executed query
    rows = cursor.fetchall()

    # Column names for the result set
    columns = [desc[0] for desc in cursor.description]

    # Convert rows to a list of dictionaries
    user_details = [dict(zip(columns, row)) for row in rows]

    # Close the database connection
    cursor.close()
    conn.close()

    return user_details

@app.route('/api/users', methods=['GET'])
def get_all_users():
    user_details = fetch_user_details()
    return jsonify(user_details)

@app.route('/api/users/<int:user_id>', methods=['GET'])
def get_user(user_id):
    user_details = fetch_user_details(user_id)
    if user_details:
        return jsonify(user_details[0])
    else:
        return jsonify({"error": "User not found"}), 404

if __name__ == '__main__':
    app.run(debug=True)
