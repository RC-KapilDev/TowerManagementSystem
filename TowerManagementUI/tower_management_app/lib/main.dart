import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/equipment/cubit/equipment_cubit.dart';
import 'package:tower_management_app/Technician/login/LoginPage.dart';
import 'package:tower_management_app/Technician/login/cubit/auth_cubit.dart';
import 'package:tower_management_app/Technician/maintenance/cubit/maintenance_cubit.dart';
import 'package:tower_management_app/Technician/workorder/cubit/work_order_cubit.dart';

void main() {
  runApp(
    MultiBlocProvider(
      providers: [
        BlocProvider(create: (context) => AuthCubit()),
        BlocProvider(create: (context) => WorkOrderCubit()),
        BlocProvider(create: (context) => MaintenanceCubit()),
        BlocProvider(create: (context) => EquipmentCubit())
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        home: TechnicianLoginPage(), // Pass the userId here
      ),
    ),
  );
}
