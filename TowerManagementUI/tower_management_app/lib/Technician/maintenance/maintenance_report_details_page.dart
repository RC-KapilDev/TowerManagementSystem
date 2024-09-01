import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/maintenance/cubit/maintenance_cubit.dart';

class MaintenanceDetailPage extends StatelessWidget {
  final Map<String, dynamic> maintenanceReport;
  final String userId;

  MaintenanceDetailPage(
      {required this.maintenanceReport, required this.userId});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Maintenance Report Details'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: BlocConsumer<MaintenanceCubit, MaintenanceState>(
          listener: (context, state) {
            if (state is MaintenanceDeleted) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(
                    content: Text('Maintenance report deleted successfully')),
              );
              Navigator.of(context).pop(true); // Go back and indicate success
            } else if (state is MaintenanceError) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text(state.message)),
              );
            }
          },
          builder: (context, state) {
            return Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Maintenance ID: ${maintenanceReport['maintenanceId']}',
                    style:
                        TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                SizedBox(height: 10),
                Text('Work Order ID: ${maintenanceReport['workOrder']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text('Tower ID: ${maintenanceReport['towerInfo']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text(
                    'Equipment Required: ${maintenanceReport['equipmentRequired']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text('Issues Faced: ${maintenanceReport['issuesFaced']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text('Priority: ${maintenanceReport['priority']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text('Created At: ${maintenanceReport['createdAt']}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 10),
                Text(
                    'Deleted Status: ${maintenanceReport['deletedStatus'] ? 'Yes' : 'No'}',
                    style: TextStyle(fontSize: 16)),
                SizedBox(height: 20),
                Center(
                  child: ElevatedButton(
                    onPressed: () {
                      context.read<MaintenanceCubit>().deleteMaintenanceReport(
                          maintenanceReport['maintenanceId'].toString(),
                          userId);
                    },
                    child: Text('Delete Report'),
                    style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.white,
                      backgroundColor: Colors.red, // Text color
                    ),
                  ),
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
