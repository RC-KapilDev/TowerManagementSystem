import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/workorder/work_order_list.dart';
import 'package:tower_management_app/Technician/workorder/cubit/work_order_cubit.dart';

class WorkOrderPageHistory extends StatelessWidget {
  const WorkOrderPageHistory({super.key, required this.userId});
  final String userId;

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => WorkOrderCubit()..fetchWorkOrders(userId),
      child: Scaffold(
        appBar: AppBar(
          title: Text("WorkOrder History"),
        ),
        body: BlocConsumer<WorkOrderCubit, WorkOrderState>(
          listener: (context, state) {
            if (state is WorkOrderError) {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(content: Text(state.message)),
              );
            }
          },
          builder: (context, state) {
            if (state is WorkOrdersLoaded) {
              return RefreshIndicator(
                onRefresh: () async {
                  context.read<WorkOrderCubit>().fetchWorkOrders(userId);
                },
                child: Column(
                  children: [
                    WorkOrderList(
                      status: false,
                      userId: userId,
                    ),
                  ],
                ),
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
