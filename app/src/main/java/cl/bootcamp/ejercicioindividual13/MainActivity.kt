package cl.bootcamp.ejercicioindividual13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import cl.bootcamp.ejercicioindividual13.navigation.NavManager
import cl.bootcamp.ejercicioindividual13.ui.theme.EjercicioIndividual13Theme
import cl.bootcamp.ejercicioindividual13.viewModel.PatientsListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: PatientsListViewModel by viewModels()
        setContent {
            EjercicioIndividual13Theme {
                NavManager(viewModel)
            }
        }
    }
}
