import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/maintenance/create_new_maintenance_report.dart';
import 'package:tower_management_app/Technician/maintenance/cubit/maintenance_cubit.dart';
import 'package:tower_management_app/Technician/maintenance/maintenance_list.dart';

class MaintenanceReportPage extends StatelessWidget {
  const MaintenanceReportPage({super.key, required this.userId});
  final String userId;

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => MaintenanceCubit()..fetchMaintenanceReports(userId),
      child: Scaffold(
        appBar: AppBar(
          title: Text("Maintenance Report Management Page"),
        ),
        body: BlocConsumer<MaintenanceCubit, MaintenanceState>(
          listener: (context, state) {
            if (state is MaintenanceError) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text(state.message)),
              );
            } else if (state is MaintenanceCreated) {
              // Refresh the list when a new report is created
              context.read<MaintenanceCubit>().fetchMaintenanceReports(userId);
            }
          },
          builder: (context, state) {
            if (state is MaintenanceLoading) {
              return Center(child: CircularProgressIndicator());
            } else if (state is MaintenanceLoaded) {
              return RefreshIndicator(
                onRefresh: () async {
                  context
                      .read<MaintenanceCubit>()
                      .fetchMaintenanceReports(userId);
                },
                child: Column(
                  children: [
                    MaintenanceList(userId: userId),
                  ],
                ),
              );
            } else {
              return Center(child: Text("No maintenance reports available"));
            }
          },
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            final result = await Navigator.of(context).push(
              MaterialPageRoute(
                builder: (context) => CreateMaintenanceReportPage(
                  userId: userId,
                ),
              ),
            );

            // Check if the result is true and then refresh the list
            if (result == true) {
              context.read<MaintenanceCubit>().fetchMaintenanceReports(userId);
            }
          },
          child: Icon(Icons.add),
          tooltip: 'Add Maintenance Report',
        ),
      ),
    );
  }
}
