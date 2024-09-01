import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:tower_management_app/Technician/equipment/equipment_home_page.dart';
import 'package:tower_management_app/Technician/maintenance/maintenance_report_page.dart';

import 'package:tower_management_app/Technician/workorder/work_order_page.dart';

class CustomDrawer extends StatelessWidget {
  final FlutterSecureStorage storage;
  final String userId;
  CustomDrawer({required this.storage, required this.userId});

  Future<Map<String, String>> _getUserInfo() async {
    final username = await storage.read(key: "username");
    final role = await storage.read(key: "role");
    return {
      "username": username ?? "No Info",
      "role": role ?? "No Info",
    };
  }

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          DrawerHeader(
            decoration: BoxDecoration(
              color: Colors.blue,
            ),
            child: FutureBuilder<Map<String, String>>(
              future: _getUserInfo(),
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return CircularProgressIndicator();
                } else if (snapshot.hasData) {
                  final username = snapshot.data!['username'];
                  final role = snapshot.data!['role'];
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Welcome, $username!',
                          style: TextStyle(fontSize: 20, color: Colors.white)),
                      SizedBox(height: 5),
                      Text('Role: $role',
                          style: TextStyle(fontSize: 16, color: Colors.white)),
                    ],
                  );
                } else {
                  return Center(child: Text('Error retrieving user info'));
                }
              },
            ),
          ),
          ListTile(
            leading: Icon(Icons.report),
            title: Text('Maintenance Report'),
            onTap: () {
              Navigator.pop(context); // Close the drawer
              // Add your navigation code here
              Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => MaintenanceReportPage(userId: userId),
                  ));
            },
          ),
          ListTile(
            leading: Icon(Icons.history),
            title: Text('Work Order History'),
            onTap: () {
              Navigator.pop(context); // Close the drawer
              // Add your navigation code here
              Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => WorkOrderPageHistory(userId: userId),
                  ));
            },
          ),
          ListTile(
            leading: Icon(Icons.build),
            title: Text('Equipment Management'),
            onTap: () {
              Navigator.pop(context); // Close the drawer
              // Add your navigation code here
              Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => EquipmentListPage(
                      userId: userId,
                    ),
                  ));
            },
          ),
        ],
      ),
    );
  }
}
