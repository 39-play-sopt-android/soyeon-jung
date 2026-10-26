package org.sopt.play.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.play.presentation.home.HomeScreen
import org.sopt.play.presentation.home.model.HomeDummyData
import org.sopt.play.presentation.login.LoginScreen
import org.sopt.play.presentation.register.RegisterScreen

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
        composable<Route.Login> { backStackEntry ->
            val login = backStackEntry.toRoute<Route.Login>()

            LoginScreen(
                registeredEmail = login.email,
                registeredPassword = login.password,
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
            RegisterScreen(
                onRegisterClick = { email, password ->
                    navController.navigate(Route.Login(email, password)) {
                        popUpTo<Route.Login> {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable<Route.Home> {
            HomeScreen(                userName = "김솝트",
                banners = HomeDummyData.banners,
                categories = HomeDummyData.categories,
                products = HomeDummyData.products,)
        }
    }
}
