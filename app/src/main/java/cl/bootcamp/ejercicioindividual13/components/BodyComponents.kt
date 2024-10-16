package cl.bootcamp.ejercicioindividual13.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRowScope
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import cl.bootcamp.ejercicioindividual13.model.PatientsListState


@Composable
fun Space(size: Dp = 5.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun MainTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick
    ) { Text(text = text) }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiChoiceSegmentedButtonRowScope.SegmentedButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    // border: BorderStroke = SegmentedButtonDefaults.borderStroke(colors.borderColor(enabled, checked)),
    interactionSource: MutableInteractionSource? = null,
    icon: @Composable () -> Unit = { SegmentedButtonDefaults.Icon(checked) },
    label: @Composable () -> Unit

) {

}

@Composable
fun MainText(
    text: String
) {
    Text(
        text = "$text",
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}


@Composable
fun Alert(
    title: String,
    msj: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = msj) },
        shape = CutCornerShape(10.dp),
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = confirmText)
            }
        }
    )
}

@Composable
fun MainFloating(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.Magenta,
        contentColor = Color.White
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Composable
fun CardPatient(
    item: PatientsListState,
    onClick: () -> Unit

) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .border(
                border = BorderStroke(
                    2.dp, Color(0XFF018786)
                ),
                shape = RoundedCornerShape(20)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.name,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Button(
                onClick = onClick
            ) { Text(text = "Calcular IMC") }
        }

    }
}

@Composable
fun Modal(
    title: String,
    onDismissClick: () -> Unit,
    onConfirmClick: @Composable () -> Unit,
    onText: @Composable () -> Unit
) {
    AlertDialog(
        title = { Text(text = title) },
        shape = RoundedCornerShape(10.dp),
        onDismissRequest = onDismissClick,
        text = onText,
        confirmButton = onConfirmClick
    )
}

