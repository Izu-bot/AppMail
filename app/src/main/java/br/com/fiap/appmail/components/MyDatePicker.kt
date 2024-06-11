package br.com.fiap.appmail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    var showDialog by remember {
        mutableStateOf(false)
    }
    var descricaoEditada by remember {
        mutableStateOf("")
    }
    var itemEditando by remember {
        mutableStateOf<Calendario?>(null)
    }

    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val calendarioRepository = CalendarioRepository(context)
    var listaLembretes by rememberSaveable { mutableStateOf(calendarioRepository.getAll()) }


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
                listaLembretes = calendarioRepository.getAll()
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
        items(listaLembretes) {
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
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.data,
                        modifier = Modifier.padding(16.dp),
                        fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                    )
                    IconButton(onClick = {
                        showDialog = true
                        itemEditando = it
                        descricaoEditada = it.descricao
                    }) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "Update")
                    }
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text(text = "Editar Lembrete") },
                            text = {
                                Column {
                                    OutlinedTextField(
                                        value = descricaoEditada,
                                        onValueChange = { descricaoEditada = it },
                                        label = { Text(text = "Descrição") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Button(onClick = {
                                        itemEditando?.let { item ->
                                            val itemAtualizado = item.copy(
                                                descricao = descricaoEditada
                                            )
                                            calendarioRepository.update(itemAtualizado)
                                            listaLembretes = calendarioRepository.getAll()
                                        }
                                        showDialog = false
                                    },
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(text = "Salvar")
                                    }
                                }
                            },
                            confirmButton = {},
                            dismissButton = {}
                        )
                    }
                    IconButton(onClick = {
                        calendarioRepository.delete(it)
                        listaLembretes = calendarioRepository.getAll()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
                Divider(
                    color = Color.Black,
                )
                Text(
                    text = it.descricao.lowercase().replaceFirstChar { char -> char.uppercase() },
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
