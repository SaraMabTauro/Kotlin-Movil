package com.example.habitos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habitos.data.api.AuthService
import com.example.habitos.ui.habitlist.HabitListScreen
import com.example.habitos.ui.login.LoginScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login",
    authService: AuthService
) {
    val actions = remember(navController) { NavigationActions(navController) }
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                navController = navController,
                authService = authService,
                onLoginSuccess = { token ->
                    // Guardar el token y navegar a la lista de hábitos
                    actions.navigateToHabitList()
                }
            )
        }
        
        composable("habitList") {
            HabitListScreen(
                navController = navController
            )
        }
        
        composable("habitDetail/{habitId}") { backStackEntry ->
            val habitId = backStackEntry.arguments?.getString("habitId")
            HabitDetailScreen(
                navController = navController,
                habitId = habitId
            )
        }
    }
}


class NavigationActions(private val navController: NavHostController) {
    
    fun navigateToHabitList() {
        navController.navigate("habitList") {
            popUpTo("login") { inclusive = true }
        }
    }
    
    fun navigateToHabitDetail(habitId: String? = null) {
        navController.navigate("habitDetail/${habitId ?: "new"}")
    }
    
    fun navigateBack() {
        navController.popBackStack()
    }
}