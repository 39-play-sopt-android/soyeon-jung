package org.sopt.play.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.play.presentation.home.HomeRoute
import org.sopt.play.presentation.login.LoginRoute
import org.sopt.play.presentation.register.RegisterRoute

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = Route.Login(),
        modifier = modifier,
    ) {
        composable<Route.Login> {
            LoginRoute(
                onLoginSuccess = {
                    navController.navigate(Route.Home) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Route.Register)
                },
            )
        }

        composable<Route.Register> {
            RegisterRoute(
                onRegisterSuccess = { email, password ->
                    navController.navigate(Route.Login(email, password)) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable<Route.Home> {
            HomeRoute()
        }
    }
}
