package br.com.fiap.appmail.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.appmail.ui.theme.AppMailTheme

@Composable
fun BarraPesquisa(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    colors: TextFieldColors,
    modifier: Modifier
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        placeholder = {
            Text(text = placeholder)
        },
        colors = colors
    )
}

@Preview(showBackground = true)
@Composable
private fun BarraPesquisaPreview() {
    AppMailTheme {
//        BarraPesquisa()
    }
}