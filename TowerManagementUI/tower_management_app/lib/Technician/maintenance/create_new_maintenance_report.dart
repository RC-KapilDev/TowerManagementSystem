import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/maintenance/cubit/maintenance_cubit.dart';
import 'package:dio/dio.dart';
import 'package:tower_management_app/config/constants.dart';

class CreateMaintenanceReportPage extends StatefulWidget {
  final String userId;

  CreateMaintenanceReportPage({required this.userId});

  @override
  _CreateMaintenanceReportPageState createState() =>
      _CreateMaintenanceReportPageState();
}

class _CreateMaintenanceReportPageState
    extends State<CreateMaintenanceReportPage> {
  final _formKey = GlobalKey<FormState>();
  final _equipmentController = TextEditingController();
  final _issuesController = TextEditingController();
  final _workOrderController = TextEditingController();
  String _selectedPriority = 'Low'; // Default value
  List<Map<String, dynamic>> _workOrders = [];
  int? _selectedTowerId;

  final List<String> _priorities = ['High', 'Low', 'Mid'];

  @override
  void initState() {
    super.initState();
    _workOrderController.addListener(_onWorkOrderChanged);
  }

  @override
  void dispose() {
    _equipmentController.dispose();
    _issuesController.dispose();
    _workOrderController.dispose();
    super.dispose();
  }

  void _onWorkOrderChanged() async {
    final query = _workOrderController.text;
    if (query.isNotEmpty) {
      final response =
          await Dio().get('${Constants.url}workorders/user/${widget.userId}');
      final workOrders = List<Map<String, dynamic>>.from(response.data);
      setState(() {
        _workOrders = workOrders
            .where((order) => order['workorderId'].toString().contains(query))
            .toList();
      });
    } else {
      setState(() {
        _workOrders = [];
      });
    }
  }

  void _onWorkOrderSelected(Map<String, dynamic> selectedOrder) {
    setState(() {
      _workOrderController.text = selectedOrder['workorderId'].toString();
      _selectedTowerId = selectedOrder['tower']; // Autofill towerId
      _workOrders = []; // Hide suggestions
    });
  }

  void _submit() {
    if (_formKey.currentState!.validate()) {
      final equipmentRequired = _equipmentController.text;
      final issuesFaced = _issuesController.text;
      final user = int.parse(widget.userId) ?? 0;
      final workOrder = int.tryParse(_workOrderController.text) ?? 0;
      final towerInfo = _selectedTowerId ?? 0;

      context.read<MaintenanceCubit>().createMaintenanceReport(
            user: user,
            workOrder: workOrder,
            towerInfo: towerInfo,
            equipmentRequired: equipmentRequired,
            issuesFaced: issuesFaced,
            priority: _selectedPriority,
          );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Create Maintenance Report'),
      ),
      body: BlocListener<MaintenanceCubit, MaintenanceState>(
        listener: (context, state) {
          if (state is MaintenanceCreated) {
            // Show a success Snackbar and pop the page
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(content: Text(state.message)),
            );
            Navigator.of(context).pop(
                true); // Pass true if you want to refresh the previous page
          } else if (state is MaintenanceError) {
            // Show an error Snackbar
            ScaffoldMessenger.of(context).showSnackBar(
              SnackBar(content: Text(state.message)),
            );
          }
        },
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: SingleChildScrollView(
            child: Form(
              key: _formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  TextFormField(
                    controller: _workOrderController,
                    decoration: InputDecoration(labelText: 'Work Order ID'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter a work order ID';
                      }
                      return null;
                    },
                  ),
                  if (_workOrders.isNotEmpty) ...[
                    SizedBox(height: 8),
                    Container(
                      height: 150,
                      child: ListView.builder(
                        itemCount: _workOrders.length,
                        itemBuilder: (context, index) {
                          final order = _workOrders[index];
                          return ListTile(
                            title:
                                Text('Work Order ID: ${order['workorderId']}'),
                            subtitle: Text('Tower ID: ${order['tower']}'),
                            onTap: () => _onWorkOrderSelected(order),
                          );
                        },
                      ),
                    ),
                  ],
                  SizedBox(height: 16),
                  TextFormField(
                    controller: _equipmentController,
                    decoration:
                        InputDecoration(labelText: 'Equipment Required'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please enter the equipment required';
                      }
                      return null;
                    },
                  ),
                  SizedBox(height: 16),
                  TextFormField(
                    controller: _issuesController,
                    decoration: InputDecoration(labelText: 'Issues Faced'),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please describe the issues faced';
                      }
                      return null;
                    },
                  ),
                  SizedBox(height: 16),
                  DropdownButtonFormField<String>(
                    value: _selectedPriority,
                    onChanged: (String? newValue) {
                      setState(() {
                        _selectedPriority = newValue!;
                      });
                    },
                    decoration: InputDecoration(labelText: 'Priority'),
                    items: _priorities.map((String priority) {
                      return DropdownMenuItem<String>(
                        value: priority,
                        child: Text(priority),
                      );
                    }).toList(),
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Please select a priority';
                      }
                      return null;
                    },
                  ),
                  SizedBox(height: 24),
                  ElevatedButton(
                    onPressed: _submit,
                    child: Text('Submit'),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
