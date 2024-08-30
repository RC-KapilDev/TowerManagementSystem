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

def fetch_work_order_details(workorder_id=None):
    # Connect to the PostgreSQL database
    conn = psycopg2.connect(
        host=DB_HOST,
        port=DB_PORT,
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASSWORD
    )
    cursor = conn.cursor()

    # Query to fetch work order details
    query = "SELECT * FROM work_order"
    params = []

    if workorder_id:
        query += " WHERE workorder_id = %s"
        params.append(workorder_id)

    # Execute the query
    cursor.execute(query, params)

    # Fetch all rows from the executed query
    rows = cursor.fetchall()

    # Column names for the result set
    columns = [desc[0] for desc in cursor.description]

    # Convert rows to a list of dictionaries
    work_order_details = [dict(zip(columns, row)) for row in rows]

    # Close the database connection
    cursor.close()
    conn.close()

    return work_order_details

@app.route('/api/work_orders', methods=['GET'])
def get_all_work_orders():
    work_order_details = fetch_work_order_details()
    return jsonify(work_order_details)

@app.route('/api/work_orders/<int:workorder_id>', methods=['GET'])
def get_work_order(workorder_id):
    work_order_details = fetch_work_order_details(workorder_id)
    if work_order_details:
        return jsonify(work_order_details[0])
    else:
        return jsonify({"error": "Work order not found"}), 404

if __name__ == '__main__':
    app.run(debug=True,port=5001)
