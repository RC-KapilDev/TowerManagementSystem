import 'package:bloc/bloc.dart';

import 'package:dio/dio.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:tower_management_app/config/constants.dart';

// Define the states
abstract class AuthState {}

class AuthInitial extends AuthState {}

class AuthLoading extends AuthState {}

class AuthAuthenticated extends AuthState {
  final String userId;
  AuthAuthenticated(this.userId);
}

class AuthUnauthenticated extends AuthState {}

class AuthError extends AuthState {
  final String message;
  AuthError(this.message);
}

class AuthCubit extends Cubit<AuthState> {
  final Dio _dio = Dio();
  final FlutterSecureStorage _storage = FlutterSecureStorage();

  AuthCubit() : super(AuthInitial());

  Future<void> login(String username, String password) async {
    emit(AuthLoading());
    try {
      final response = await _dio.post(
        '${Constants.url}users/authenticate',
        queryParameters: {
          'username': username,
          'password': password,
        },
      );

      if (response.statusCode == 200) {
        await _storage.write(
            key: "userId", value: response.data['userId'].toString());
        await _storage.write(key: "username", value: response.data['username']);
        await _storage.write(key: "name", value: response.data['name']);
        await _storage.write(key: "email", value: response.data['email']);
        await _storage.write(
            key: "specialisation", value: response.data['specialisation']);
        await _storage.write(key: "location", value: response.data['location']);
        await _storage.write(key: "role", value: response.data['role']);
        await _storage.write(
            key: "pincode", value: response.data['pincode'].toString());
        await _storage.write(
            key: "activeStatus",
            value: response.data['activeStatus'].toString());

        emit(AuthAuthenticated(response.data['userId'].toString()));
      } else {
        emit(AuthUnauthenticated());
      }
    } on DioError catch (e) {
      if (e.response?.statusCode == 404) {
        emit(AuthError('User not found'));
      } else {
        emit(AuthError('An error occurred'));
      }
    }
  }

  Future<void> logout() async {
    await _storage.deleteAll();
    emit(AuthUnauthenticated());
  }

  Future<void> checkAuthentication() async {
    final userId = await _storage.read(key: "userId");
    if (userId != null) {
      emit(AuthAuthenticated(userId));
    } else {
      emit(AuthUnauthenticated());
    }
  }
}
