import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:tower_management_app/config/constants.dart';

class EquipmentDetailsPage extends StatefulWidget {
  final Map<String, dynamic> equipment;

  EquipmentDetailsPage({required this.equipment});

  @override
  _EquipmentDetailsPageState createState() => _EquipmentDetailsPageState();
}

class _EquipmentDetailsPageState extends State<EquipmentDetailsPage> {
  bool _isClaimed = false;
  bool _isButtonDisabled = false;
  String _errorMessage = '';

  @override
  void initState() {
    super.initState();
    _isClaimed = widget.equipment['claimed'];
  }

  Future<void> _updateClaimedStatus() async {
    final equipmentId = widget.equipment['equipmentId'];
    final url = '${Constants.url}equipments/$equipmentId/claim';

    try {
      final response = await Dio().patch(url);

      if (response.statusCode == 204) {
        setState(() {
          _isClaimed = true;
          _isButtonDisabled = true;
          _errorMessage = ''; // Clear any previous error messages
        });
      } else {
        // Handle other status codes if needed
        setState(() {
          _errorMessage =
              'Failed to claim equipment. Status code: ${response.statusCode}';
        });
      }
    } catch (e) {
      // Handle error (e.g., show a message to the user)
      setState(() {
        _errorMessage = 'Error updating claimed status: $e';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Equipment Details'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Equipment Name: ${widget.equipment['equipmentName']}',
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Model: ${widget.equipment['model']}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Serial Number: ${widget.equipment['serialNumber']}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Manufacture: ${widget.equipment['manufacture']}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Created At: ${widget.equipment['createdAt']}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Claimed: ${_isClaimed ? 'Yes' : 'No'}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 8),
            Text(
              'Deleted Status: ${widget.equipment['deletedStatus'] ? 'Yes' : 'No'}',
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[700],
              ),
            ),
            SizedBox(height: 16),
            if (!_isClaimed && !_isButtonDisabled)
              ElevatedButton(
                onPressed: _updateClaimedStatus,
                child: Text('Claim Equipment'),
              ),
            if (_errorMessage.isNotEmpty)
              Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: Text(
                  _errorMessage,
                  style: TextStyle(
                    color: Colors.red,
                    fontSize: 16,
                  ),
                ),
              ),
          ],
        ),
      ),
    );
  }
}
