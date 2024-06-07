package br.com.fiap.appmail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.appmail.database.repository.CalendarioRepository
import br.com.fiap.appmail.model.Calendario
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePicker(modifier: Modifier = Modifier) {

    var selectedDate by remember {
        mutableStateOf("")
    }
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    var descricao by remember {
        mutableStateOf("")
    }

    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val calendarioRepository = CalendarioRepository(context)

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate = millis.toBrazilianDateFormat()
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    TextField(
        value = selectedDate,
        onValueChange = { },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus()
                }
            },
        label = {
            Text(text = "Date")
        },
        readOnly = true
    )
    MyOutlined(
        value = descricao,
        onValueChange = { descricao = it },
        label = "Descrição",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        maxLines = 4,
        modifier = Modifier
            .padding(5.dp)
            .height(60.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
    )
    
    IconButton(
        onClick = {
                  if (selectedDate.isNotEmpty() and descricao.isNotEmpty()) {
                      val enviarCalendario = Calendario(
                          data = selectedDate,
                          descricao = descricao
                      )
                      calendarioRepository.save(enviarCalendario)
                      selectedDate = ""
                      descricao = ""
                      calendarioRepository.getAll()
                  } else {
                      return@IconButton
                  }
        },
        modifier.size(60.dp)
    ) {
        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Accept")
    }
    
    Spacer(modifier.padding(10.dp))
    Divider()
    Spacer(modifier.padding(10.dp))
    Text(
        text = "Agenda",
        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
        fontSize = 22.sp,
        modifier = modifier.padding(16.dp)
    )
    LazyColumn {
        items(calendarioRepository.getAll()) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                Text(
                    text = it.data,
                    modifier = Modifier.padding(16.dp),
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                )
                Divider(
                    color = Color.Black,
                )
                Text(
                    text = it.descricao,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }

}

// Função para formatar a data para o padrão brasileiro
fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}
