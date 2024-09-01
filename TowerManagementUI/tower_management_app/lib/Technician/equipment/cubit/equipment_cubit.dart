import 'package:bloc/bloc.dart';
import 'package:dio/dio.dart';
import 'package:equatable/equatable.dart';
import 'package:tower_management_app/config/constants.dart';

part 'equipment_state.dart';

class EquipmentCubit extends Cubit<EquipmentState> {
  final Dio _dio = Dio();

  EquipmentCubit() : super(EquipmentInitial());

  Future<void> fetchEquipments(String userId) async {
    try {
      emit(EquipmentLoading());
      final response =
          await _dio.get('${Constants.url}equipments/user/$userId');
      final List<dynamic> data = response.data;

      final equipments = data.cast<Map<String, dynamic>>();
      emit(EquipmentLoaded(equipments));
    } catch (e) {
      emit(EquipmentError(e.toString()));
    }
  }
}
