import 'package:flutter/material.dart';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';

class ProfilePage extends StatelessWidget {
  final storage = FlutterSecureStorage();

  Future<Map<String, String?>> _getUserInfo() async {
    final userId = await storage.read(key: "userId");
    final username = await storage.read(key: "username");
    final name = await storage.read(key: "name");
    final email = await storage.read(key: "email");
    final specialisation = await storage.read(key: "specialisation");
    final location = await storage.read(key: "location");
    final role = await storage.read(key: "role");
    final pincode = await storage.read(key: "pincode");
    final activeStatus = await storage.read(key: "activeStatus");

    return {
      "userId": userId,
      "username": username,
      "name": name,
      "email": email,
      "specialisation": specialisation,
      "location": location,
      "role": role,
      "pincode": pincode,
      "activeStatus": activeStatus,
    };
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('User Profile'),
      ),
      body: FutureBuilder<Map<String, String?>>(
        future: _getUserInfo(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return CircularProgressIndicator();
          } else if (snapshot.hasData) {
            final userInfo = snapshot.data!;
            return Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('User ID: ${userInfo['userId']}'),
                  Text('Username: ${userInfo['username']}'),
                  Text('Name: ${userInfo['name']}'),
                  Text('Email: ${userInfo['email']}'),
                  Text('Specialisation: ${userInfo['specialisation']}'),
                  Text('Location: ${userInfo['location']}'),
                  Text('Role: ${userInfo['role']}'),
                  Text('Pincode: ${userInfo['pincode']}'),
                  Text('Active Status: ${userInfo['activeStatus']}'),
                ],
              ),
            );
          } else {
            return Text('Error retrieving user info');
          }
        },
      ),
    );
  }
}
