package org.sopt.play.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.play.presentation.home.HomeScreen
import org.sopt.play.presentation.login.LoginScreen
import org.sopt.play.presentation.register.RegisterScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Login(),
        modifier = modifier,
    ) {
        composable<Login> { backStackEntry ->
            val login = backStackEntry.toRoute<Login>()

            LoginScreen(
                onLoginClick = { email, password ->
                    if (email == login.email && password == login.password) {
                        navController.navigate(Home) {
                            popUpTo<Login> {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "이메일 또는 비밀번호가 올바르지 않아요.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                },
                onRegisterClick = {
                    navController.navigate(Register)
                },
            )
        }

        composable<Register> {
            RegisterScreen(
                onRegisterClick = { email, password ->
                    navController.navigate(Login(email, password)) {
                        popUpTo<Login> {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable<Home> {
            HomeScreen()
        }
    }
}
