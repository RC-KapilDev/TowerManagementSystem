import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/workorder/cubit/work_order_cubit.dart';

class WorkOrderDetailPage extends StatelessWidget {
  final Map<String, dynamic> workOrder;
  final String userId;
  final bool isCompeletedBth;

  WorkOrderDetailPage(
      {required this.workOrder,
      required this.userId,
      required this.isCompeletedBth});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Work Order Details'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: BlocConsumer<WorkOrderCubit, WorkOrderState>(
          listener: (context, state) {
            if (state is WorkOrderCompleted) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text(state.message)),
              );
              Navigator.of(context).pop(true); // Go back and indicate success
            } else if (state is WorkOrderError) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text(state.message)),
              );
            }
          },
          builder: (context, state) {
            if (state is WorkOrdersLoaded) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Workorder ID: ${workOrder['workorderId']}',
                      style:
                          TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                  SizedBox(height: 10),
                  Text('Tower ID: ${workOrder['tower']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('User ID: ${workOrder['user']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Task: ${workOrder['taskDescription']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Scheduled Date: ${workOrder['scheduledDate']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Status: ${workOrder['status']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Completed Date: ${workOrder['completedDate'] ?? 'N/A'}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Created At: ${workOrder['createdAt']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 10),
                  Text('Updated At: ${workOrder['updatedAt']}',
                      style: TextStyle(fontSize: 16)),
                  SizedBox(height: 20),
                  if (isCompeletedBth)
                    Center(
                      child: ElevatedButton(
                        onPressed: () {
                          context.read<WorkOrderCubit>().markAsCompleted(
                              workOrder['workorderId'].toString(), userId);
                        },
                        child: Text('Mark as Completed'),
                      ),
                    )
                ],
              );
            } else if (state is WorkOrderError) {
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
