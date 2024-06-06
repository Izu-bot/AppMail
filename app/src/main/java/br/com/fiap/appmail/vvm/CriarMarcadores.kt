package br.com.fiap.appmail.vvm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.appmail.database.repository.MarcadorRepository
import br.com.fiap.appmail.modal.MarcadorPersonalizado

@Composable
fun CriarMarcadores(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val marcadoresRepository = MarcadorRepository(context)

    var marcador by rememberSaveable { mutableStateOf("") }
    var marcadores by rememberSaveable { mutableStateOf(marcadoresRepository.getAllMarcadores()) }

    Column {
        Spacer(
            modifier = Modifier
                .height(50.dp)
                .padding(16.dp)
        )
        Text(
            text = "Marcadores",
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = marcador,
                onValueChange = { marcador = it },
                label = { Text(text = "Nome do marcador") },
                modifier = Modifier.padding(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                )
            )
            Button(
                onClick = {
                    if (marcador.isNotEmpty()) {
                        val enviarMarcador = MarcadorPersonalizado(
                            nomeMarcador = marcador
                        )
                        marcadoresRepository.insert(enviarMarcador)
                        marcador = ""
                    } else {
                        return@Button
                    }
                    marcadores = marcadoresRepository.getAllMarcadores()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            Text(
                text = "Seus marcadores",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle
            )

            LazyColumn {
                items(marcadores) { marcador ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = Color.Black
                        )
                    ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = (marcador.nomeMarcador.lowercase().replaceFirstChar { char -> char.uppercase() }),
                                    fontSize = 30.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(10.dp)
                                )
                                IconButton(
                                    onClick = {
                                        marcadoresRepository.delete(marcador)
                                        marcadores = marcadoresRepository.getAllMarcadores()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Deletar"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
