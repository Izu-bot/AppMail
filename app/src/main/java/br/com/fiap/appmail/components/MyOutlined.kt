package br.com.fiap.appmail.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MyOutlined(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    maxLines: Int = 1,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        shape = shape,
        maxLines = maxLines,
    )
}


//keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)