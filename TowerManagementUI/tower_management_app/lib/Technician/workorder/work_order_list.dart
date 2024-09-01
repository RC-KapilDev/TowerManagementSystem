import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/workorder/cubit/work_order_cubit.dart';
import 'package:tower_management_app/Technician/workorder/WorkOrderDetailPage.dart';

class WorkOrderList extends StatelessWidget {
  final String userId;
  final bool status;

  WorkOrderList({required this.status, required this.userId});

  @override
  Widget build(BuildContext context) {
    return Expanded(
        child: BlocProvider(
      create: (context) => WorkOrderCubit()..fetchWorkOrders(userId),
      child: BlocBuilder<WorkOrderCubit, WorkOrderState>(
        builder: (context, state) {
          if (state is WorkOrdersLoaded) {
            final pendingWorkOrders = status
                ? state.workOrders
                    .where((workOrder) =>
                        workOrder['status'].toString().toLowerCase() !=
                        'completed')
                    .toList()
                : state.workOrders.toList();

            if (pendingWorkOrders.isEmpty) {
              return ListView(
                children: [
                  SizedBox(
                    height: MediaQuery.of(context).size.height / 2,
                    child: const Center(
                      child: Text(
                        'No pending work orders. Keep it up!',
                        style: TextStyle(
                            fontSize: 15, fontWeight: FontWeight.w500),
                      ),
                    ),
                  ),
                ],
              );
            }

            return ListView.builder(
              itemCount: pendingWorkOrders.length,
              itemBuilder: (context, index) {
                final workOrder = pendingWorkOrders[index];
                return ListTile(
                  leading: Icon(Icons.work),
                  title: Text(workOrder['taskDescription']),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Status: ${workOrder['status']}'),
                      Text('Work Order ID: ${workOrder['workorderId']}'),
                    ],
                  ),
                  trailing: Icon(Icons.arrow_forward_ios),
                  onTap: () async {
                    final result = await Navigator.of(context).push(
                      MaterialPageRoute(
                        builder: (context) => WorkOrderDetailPage(
                          workOrder: workOrder,
                          userId: userId,
                          isCompeletedBth: status,
                        ),
                      ),
                    );

                    if (result == true) {
                      context.read<WorkOrderCubit>().fetchWorkOrders(userId);
                    }
                  },
                );
              },
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
    ));
  }
}
