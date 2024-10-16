package cl.bootcamp.ejercicioindividual13.view

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.bootcamp.ejercicioindividual13.components.Alert
import cl.bootcamp.ejercicioindividual13.components.MainButton
import cl.bootcamp.ejercicioindividual13.components.MainText
import cl.bootcamp.ejercicioindividual13.components.MainTextField
import cl.bootcamp.ejercicioindividual13.components.Space
import cl.bootcamp.ejercicioindividual13.viewModel.PatientsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateView(
    navController: NavController,
    viewModel: PatientsListViewModel,
    id: Int
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Ejercicio Individual 14 - Calculadora IMC",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        ContentCalculateView(it, navController, viewModel, id)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentCalculateView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: PatientsListViewModel,
    id: Int

) {
    var checkedList = remember { mutableStateListOf<Int>() }
    val options = listOf("Hombre", "Mujer")

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

// cargo las variables del data model
        val state = viewModel.state

        Text(
            text = "Calculadora de IMC",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Space()

        MultiChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    /*icon = {
                        SegmentedButtonDefaults.Icon(active = index in checkedList) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    },*/
                    onCheckedChange = {

                        for (i in 0..options.size) {
                            checkedList.remove(i)
                        }
                        checkedList.add(index)

                    },
                    checked = index in checkedList
                ) {
                    Text(label)
                }
            }
        }

        Space()
        MainTextField(
            value = state.age,
            onValueChange = { viewModel.onValue(it, "age") },
            label = "Edad"
        )
        Space()
        MainTextField(
            value = state.weight,
            onValueChange = { viewModel.onValue(it, "weight") },
            label = "Peso (kg)"
        )
        Space()
        MainTextField(
            value = state.height,
            onValueChange = { viewModel.onValue(it, "height") },
            label = "Altura (cm)"
        )
        Space()
        MainButton(
            text = "Calcular"
        ) {
            // Calcular el IMC
            viewModel.calculate()
        }

        Space()

        MainText(text = state.bmi)

        if (state.flagAlert) {
            Alert(
                title = "Mensaje de Alerta",
                msj = "Debe Ingresar la altura",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel.closeAlert() }
            ) { }
        }
    }


}
