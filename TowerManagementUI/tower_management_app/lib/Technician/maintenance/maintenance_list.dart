import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import 'package:tower_management_app/Technician/maintenance/cubit/maintenance_cubit.dart';
import 'package:tower_management_app/Technician/maintenance/maintenance_report_details_page.dart';

class MaintenanceList extends StatefulWidget {
  final String userId;

  MaintenanceList({required this.userId});

  @override
  State<MaintenanceList> createState() => _MaintenanceListState();
}

class _MaintenanceListState extends State<MaintenanceList> {
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: RefreshIndicator(
        onRefresh: () async {
          context
              .read<MaintenanceCubit>()
              .fetchMaintenanceReports(widget.userId);
        },
        child: BlocBuilder<MaintenanceCubit, MaintenanceState>(
          builder: (context, state) {
            if (state is MaintenanceLoaded) {
              final maintenanceReports = state.reports.toList();

              if (maintenanceReports.isEmpty) {
                return ListView(
                  children: [
                    SizedBox(
                      height: MediaQuery.of(context).size.height / 2,
                      child: const Center(
                        child: Text(
                          'No maintenance reports!!',
                          style: TextStyle(
                              fontSize: 15, fontWeight: FontWeight.w500),
                        ),
                      ),
                    ),
                  ],
                );
              }

              return ListView.builder(
                itemCount: maintenanceReports.length,
                itemBuilder: (context, index) {
                  final maintenanceReport = maintenanceReports[index];
                  return ListTile(
                    leading: Icon(Icons.work),
                    title: Text(maintenanceReport['issuesFaced']),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('workOrder ID: ${maintenanceReport['workOrder']}'),
                        Text('Tower ID: ${maintenanceReport['towerInfo']}'),
                      ],
                    ),
                    trailing: Icon(Icons.arrow_forward_ios),
                    onTap: () async {
                      final result = await Navigator.of(context).push(
                        MaterialPageRoute(
                          builder: (context) => MaintenanceDetailPage(
                            maintenanceReport: maintenanceReport,
                            userId: widget.userId,
                          ),
                        ),
                      );

                      if (result == true) {
                        context
                            .read<MaintenanceCubit>()
                            .fetchMaintenanceReports(widget.userId);
                      }
                    },
                  );
                },
              );
            } else if (state is MaintenanceError) {
              return Center(
                child: Text(state.message),
              );
            } else {
              return Center(
                child: CircularProgressIndicator(),
              );
            }
          },
        ),
      ),
    );
  }
}
