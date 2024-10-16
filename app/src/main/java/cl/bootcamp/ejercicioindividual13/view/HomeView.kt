package cl.bootcamp.ejercicioindividual13.view

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.bootcamp.ejercicioindividual13.components.MainTextField
import cl.bootcamp.ejercicioindividual13.components.Space
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import cl.bootcamp.ejercicioindividual13.components.Alert
import cl.bootcamp.ejercicioindividual13.components.CardPatient
import cl.bootcamp.ejercicioindividual13.components.MainButton
import cl.bootcamp.ejercicioindividual13.components.MainFloating
import cl.bootcamp.ejercicioindividual13.components.MainText
import cl.bootcamp.ejercicioindividual13.components.Modal
import cl.bootcamp.ejercicioindividual13.viewModel.PatientsListViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.navigation.NavController
import androidx.activity.viewModels



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: PatientsListViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Ejercicio Individual 14 - Lista de Pacientes",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        ContentHomeView(it, navController, viewModel)
    }
}

@Composable
fun ContentHomeView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: PatientsListViewModel
) {

    // cargo las variables del data model
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        MainFloating(onClick = {viewModel.openModal()} )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.listPatients) { item ->
                CardPatient(
                    item = item,
                    onClick = {

                        navController.navigate("Calculate/${state.id}")
                    }
                )
            }
        }

    }

    if (state.flagModal) {
        Modal(
            title = "Nuevo Paciente",
            onDismissClick = { viewModel.closeModal() },
            onText = {
                Column {
                    OutlinedTextField(
                        value = state.namePatient,
                        onValueChange = { viewModel.onValue(it, "namePatient") },
                        label = { Text(text = "Paciente") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                }
            },
            onConfirmClick = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MainButton(
                        text = "Cerrar"
                    ) { viewModel.closeModal() }

                    MainButton(
                        text = "Agregar"
                    ) {
                        if (state.namePatient.isNotBlank()) {
                            viewModel.PatientsAdd(
                                state.namePatient
                            )
                        }
                        viewModel.closeModal()
                        viewModel.cleanState()
                    }
                }
            }
        )
    }


    /*TitleView("Home View")
Space()
MainButton("Details View", Color.White) {
    navController.navigate("Details/${id}/?${nombre}")*/

}
