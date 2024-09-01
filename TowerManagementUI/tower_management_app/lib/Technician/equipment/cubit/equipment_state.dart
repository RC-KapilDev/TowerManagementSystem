part of 'equipment_cubit.dart';

abstract class EquipmentState extends Equatable {
  const EquipmentState();

  @override
  List<Object> get props => [];
}

class EquipmentInitial extends EquipmentState {}

class EquipmentLoading extends EquipmentState {}

class EquipmentLoaded extends EquipmentState {
  final List<Map<String, dynamic>> equipments;

  const EquipmentLoaded(this.equipments);

  @override
  List<Object> get props => [equipments];
}

class EquipmentError extends EquipmentState {
  final String message;

  const EquipmentError(this.message);

  @override
  List<Object> get props => [message];
}
