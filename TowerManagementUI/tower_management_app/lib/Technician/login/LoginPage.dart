import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:tower_management_app/Technician/Home/HomePage.dart';
import 'package:tower_management_app/Technician/login/cubit/auth_cubit.dart';

class TechnicianLoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => AuthCubit()..checkAuthentication(),
      child: MaterialApp(
        title: 'Flutter Login',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: BlocBuilder<AuthCubit, AuthState>(
          builder: (context, state) {
            if (state is AuthLoading) {
              return const Scaffold(
                body: Center(
                    child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    CircularProgressIndicator(),
                    SizedBox(height: 10),
                    Text("Hang on Tight!!!")
                  ],
                )),
              );
            } else if (state is AuthAuthenticated) {
              // Passing userId to HomePage
              return HomePage(userId: state.userId);
            } else if (state is AuthUnauthenticated) {
              return const LoginPage();
            } else if (state is AuthError) {
              return LoginPage(errorMessage: state.message);
            } else {
              return Container(); // Fallback
            }
          },
        ),
      ),
    );
  }
}

class LoginPage extends StatefulWidget {
  final String? errorMessage;
  const LoginPage({super.key, this.errorMessage});

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Login')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            if (widget.errorMessage != null)
              Text(widget.errorMessage!, style: TextStyle(color: Colors.red)),
            TextField(
              controller: _usernameController,
              decoration: InputDecoration(labelText: 'Username'),
            ),
            TextField(
              controller: _passwordController,
              decoration: InputDecoration(labelText: 'Password'),
              obscureText: true,
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                final username = _usernameController.text.trim();
                final password = _passwordController.text.trim();
                context.read<AuthCubit>().login(username, password);
              },
              child: Text('Login'),
            ),
          ],
        ),
      ),
    );
  }
}
