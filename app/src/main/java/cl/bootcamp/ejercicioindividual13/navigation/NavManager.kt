package cl.bootcamp.ejercicioindividual13.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.ejercicioindividual13.view.CalculateView
import cl.bootcamp.ejercicioindividual13.view.HomeView
import cl.bootcamp.ejercicioindividual13.viewModel.PatientsListViewModel

@Composable
fun NavManager(viewModel: PatientsListViewModel) {
    val navController = rememberNavController()


    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, viewModel)
        }
        composable("Calculate/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            //navArgument("nombre") { type = NavType.StringType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            CalculateView(navController,viewModel, id )
        }
    }
}