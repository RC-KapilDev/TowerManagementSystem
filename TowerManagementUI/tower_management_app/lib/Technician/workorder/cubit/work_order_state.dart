part of 'work_order_cubit.dart';

abstract class WorkOrderState extends Equatable {
  const WorkOrderState();

  @override
  List<Object> get props => [];
}

class WorkOrderInitial extends WorkOrderState {}

class WorkOrdersLoaded extends WorkOrderState {
  final List<Map<String, dynamic>> workOrders;

  const WorkOrdersLoaded(this.workOrders);

  @override
  List<Object> get props => [workOrders];
}

class WorkOrderCompleted extends WorkOrderState {
  final String message;

  const WorkOrderCompleted(this.message);

  @override
  List<Object> get props => [message];
}

class WorkOrderError extends WorkOrderState {
  final String message;

  const WorkOrderError(this.message);

  @override
  List<Object> get props => [message];
}
