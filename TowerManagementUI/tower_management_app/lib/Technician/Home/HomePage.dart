import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:tower_management_app/Technician/workorder/cubit/work_order_cubit.dart';
import 'package:tower_management_app/Technician/Home/side_drawer.dart';
import 'package:tower_management_app/Technician/login/cubit/auth_cubit.dart';
import 'package:tower_management_app/Technician/workorder/work_order_list.dart';

class HomePage extends StatefulWidget {
  final String userId;

  HomePage({required this.userId});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final storage = FlutterSecureStorage();

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
    // Fetch work orders when the widget is built
    context.read<WorkOrderCubit>().fetchWorkOrders(widget.userId);

    return Scaffold(
      appBar: AppBar(
        title: Text('Home'),
        actions: [
          IconButton(
            icon: Icon(Icons.logout),
            onPressed: () => context.read<AuthCubit>().logout(),
          ),
        ],
      ),
      drawer: CustomDrawer(
        storage: storage,
        userId: widget.userId,
      ),
      body: RefreshIndicator(
        onRefresh: () async {
          await context.read<WorkOrderCubit>().fetchWorkOrders(widget.userId);
          setState(() {});
        },
        child: FutureBuilder<Map<String, String>>(
          future: _getUserInfo(),
          builder: (context, snapshot) {
            if (snapshot.connectionState == ConnectionState.waiting) {
              return Center(child: CircularProgressIndicator());
            } else if (snapshot.hasData) {
              final username = snapshot.data!['username'];
              final role = snapshot.data!['role'];
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Welcome, $username!',
                            style: TextStyle(fontSize: 20)),
                        SizedBox(height: 5),
                        Text('Role: $role', style: TextStyle(fontSize: 16)),
                      ],
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: Text('Pending Workorders',
                        style: TextStyle(
                            fontSize: 18, fontWeight: FontWeight.bold)),
                  ),
                  WorkOrderList(status: true, userId: widget.userId)
                ],
              );
            } else {
              return Center(child: Text('Error retrieving user info'));
            }
          },
        ),
      ),
    );
  }
}
