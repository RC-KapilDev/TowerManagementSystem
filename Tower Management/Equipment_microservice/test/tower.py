from flask import Flask, jsonify, request
import psycopg2
import json

app = Flask(__name__)

# Database connection parameters
DB_HOST = 'localhost'
DB_PORT = '5432'
DB_NAME = 'dev1'
DB_USER = 'postgres'
DB_PASSWORD = 'root'

def fetch_tower_details(tower_id=None):
    # Connect to the PostgreSQL database
    conn = psycopg2.connect(
        host=DB_HOST,
        port=DB_PORT,
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD
    )
    cursor = conn.cursor()

    # Query to fetch tower details
    query = "SELECT * FROM tower_info"
    params = []

    if tower_id:
        query += " WHERE tower_id = %s"
        params.append(tower_id)

    # Execute the query
    cursor.execute(query, params)

    # Fetch all rows from the executed query
    rows = cursor.fetchall()

    # Column names for the result set
    columns = [desc[0] for desc in cursor.description]

    # Convert rows to a list of dictionaries
    tower_details = [dict(zip(columns, row)) for row in rows]

    # Close the database connection
    cursor.close()
    conn.close()

    return tower_details

@app.route('/api/towers', methods=['GET'])
def get_all_towers():
    tower_details = fetch_tower_details()
    return jsonify(tower_details)

@app.route('/api/towers/<int:tower_id>', methods=['GET'])
def get_tower(tower_id):
    tower_details = fetch_tower_details(tower_id)
    if tower_details:
        return jsonify(tower_details[0])
    else:
        return jsonify({"error": "Tower not found"}), 404

if __name__ == '__main__':
    app.run(debug=True,port=5002)
