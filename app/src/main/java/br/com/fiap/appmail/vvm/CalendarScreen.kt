package br.com.fiap.appmail.vvm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.appmail.components.MyDatePicker

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    Column {
        Spacer(
            modifier
                .height(50.dp)
                .padding(16.dp)
        )
        MyDatePicker()
    }
}