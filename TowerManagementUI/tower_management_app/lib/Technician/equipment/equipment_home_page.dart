import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/equipment/cubit/equipment_cubit.dart';
import 'package:tower_management_app/Technician/equipment/equipment_details_page.dart'; // Replace with your path

class EquipmentListPage extends StatelessWidget {
  final String userId;

  EquipmentListPage({required this.userId});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => EquipmentCubit()..fetchEquipments(userId),
      child: Scaffold(
        appBar: AppBar(
          title: Text('Equipment List'),
        ),
        body: BlocBuilder<EquipmentCubit, EquipmentState>(
          builder: (context, state) {
            if (state is EquipmentLoading) {
              return Center(child: CircularProgressIndicator());
            } else if (state is EquipmentLoaded) {
              final equipments = state.equipments;
              return ListView.builder(
                itemCount: equipments.length,
                itemBuilder: (context, index) {
                  final equipment = equipments[index];
                  return ListTile(
                    title: Text(equipment['equipmentName']),
                    subtitle: Text('Model: ${equipment['model']}'),
                    trailing: Text('Serial: ${equipment['serialNumber']}'),
                    onTap: () {
                      Navigator.of(context).push(
                        MaterialPageRoute(
                          builder: (context) => EquipmentDetailsPage(
                            equipment: equipment,
                          ),
                        ),
                      );
                    },
                  );
                },
              );
            } else if (state is EquipmentError) {
              return Center(child: Text('Error: ${state.message}'));
            } else {
              return Center(child: Text('No data available'));
            }
          },
        ),
      ),
    );
  }
}
