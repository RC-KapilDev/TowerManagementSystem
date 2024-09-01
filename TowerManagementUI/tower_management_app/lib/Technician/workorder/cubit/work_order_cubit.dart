import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:equatable/equatable.dart';
import 'package:tower_management_app/config/constants.dart';

part 'work_order_state.dart';

class WorkOrderCubit extends Cubit<WorkOrderState> {
  final Dio _dio = Dio();

  WorkOrderCubit() : super(WorkOrderInitial());

  Future<void> fetchWorkOrders(String userId) async {
    try {
      final response =
          await _dio.get('${Constants.url}workorders/user/$userId');
      if (response.statusCode == 200) {
        emit(WorkOrdersLoaded(List<Map<String, dynamic>>.from(response.data)));
      } else {
        emit(WorkOrderError('Failed to load work orders'));
      }
    } catch (e) {
      emit(WorkOrderError('Error: $e'));
    }
  }

  Future<void> markAsCompleted(String workOrderId, String userId) async {
    final url = '${Constants.url}workorders/$workOrderId/completed-date';
    final completedDate = DateTime.now().toIso8601String();

    final body = {'completedDate': completedDate};

    try {
      final response = await _dio.patch(
        Uri.parse(url).toString(),
        options: Options(headers: {'Content-Type': 'application/json'}),
        data: body,
      );

      if (response.statusCode == 200) {
        emit(WorkOrderCompleted('Work order marked as completed!'));
        fetchWorkOrders(userId); // Refresh work orders after completion
      } else {
        emit(WorkOrderError('Failed to mark work order as completed.'));
      }
    } catch (e) {
      emit(WorkOrderError('Error: $e'));
    }
  }
}
