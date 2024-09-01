import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:equatable/equatable.dart';
import 'package:tower_management_app/config/constants.dart';

part 'maintenance_state.dart';

class MaintenanceCubit extends Cubit<MaintenanceState> {
  MaintenanceCubit() : super(MaintenanceInitial());

  Future<void> fetchMaintenanceReports(String userId) async {
    try {
      emit(MaintenanceLoading());
      final response =
          await Dio().get('${Constants.url}maintenances/user/$userId');

      if (response.statusCode == 200) {
        emit(MaintenanceLoaded(List<Map<String, dynamic>>.from(response.data)));
      } else {
        emit(MaintenanceError('Failed to load maintenance reports'));
      }
    } catch (e) {
      emit(MaintenanceError('Error: $e'));
    }
  }

  Future<void> deleteMaintenanceReport(String reportId, String userId) async {
    try {
      await Dio().delete('${Constants.url}maintenances/$reportId');
      emit(MaintenanceDeleted());
      // Optionally, refetch the list after deletion
      fetchMaintenanceReports(userId);
    } catch (e) {
      emit(MaintenanceError('Error: $e'));
    }
  }

  Future<void> createMaintenanceReport({
    required int user,
    required int workOrder,
    required int towerInfo,
    required String equipmentRequired,
    required String issuesFaced,
    required String priority,
  }) async {
    try {
      emit(MaintenanceLoading());
      final response = await Dio().post(
        '${Constants.url}maintenances',
        data: {
          'user': user,
          'workOrder': workOrder,
          'towerInfo': towerInfo,
          'equipmentRequired': equipmentRequired,
          'issuesFaced': issuesFaced,
          'priority': priority,
          // No need to send 'createdAt' and 'deletedStatus'
        },
      );

      if (response.statusCode == 200) {
        emit(MaintenanceCreated(
            message: 'Maintenance report created successfully'));
        // Optionally, navigate back or show a success message
        fetchMaintenanceReports(user.toString());
      } else {
        emit(MaintenanceError('Failed to create maintenance report'));
      }
    } catch (e) {
      emit(MaintenanceError('Error: $e'));
    }
  }
}
