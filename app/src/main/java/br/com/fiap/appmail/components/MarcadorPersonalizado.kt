package br.com.fiap.appmail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.appmail.ui.theme.AppMailTheme

@Composable
fun MarcadorPesonalizado() {
    Box(
        modifier = Modifier
            .size(16.dp)
            .offset(x = 0.dp, y = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .background(Color.Blue, shape = RoundedCornerShape(percent = 40))
        )
    }
}

@Preview
@Composable
private fun MarcadorPesonalizadoPreview() {
    AppMailTheme {
        MarcadorPesonalizado()
    }
}