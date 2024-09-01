part of 'maintenance_cubit.dart';

abstract class MaintenanceState extends Equatable {
  const MaintenanceState();

  @override
  List<Object> get props => [];
}

class MaintenanceInitial extends MaintenanceState {}

class MaintenanceLoading extends MaintenanceState {}

class MaintenanceLoaded extends MaintenanceState {
  final List<Map<String, dynamic>> reports;

  const MaintenanceLoaded(this.reports);

  @override
  List<Object> get props => [reports];
}

class MaintenanceError extends MaintenanceState {
  final String message;

  const MaintenanceError(this.message);

  @override
  List<Object> get props => [message];
}

class MaintenanceDeleted extends MaintenanceState {}

class MaintenanceCreated extends MaintenanceState {
  final String message;

  MaintenanceCreated({required this.message});
}
