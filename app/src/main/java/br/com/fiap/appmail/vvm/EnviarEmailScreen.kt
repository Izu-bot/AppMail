package br.com.fiap.appmail.vvm

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.appmail.components.MyOutlined
import br.com.fiap.appmail.database.repository.EmailRepository
import br.com.fiap.appmail.database.repository.MarcadorRepository
import br.com.fiap.appmail.modal.Email
import br.com.fiap.appmail.modal.MarcadoresEnum
import br.com.fiap.appmail.ui.theme.AppMailTheme

@Composable
fun EnviarEmailScreen(modifier: Modifier = Modifier) {

    val viewModel: EnviarEmailViewModel = viewModel()

    val contextMarcador = LocalContext.current
    val marcadorRepository = MarcadorRepository(contextMarcador)

    val context = LocalContext.current
    val emailRepository = EmailRepository(context)

    var buttonText by remember { mutableStateOf("Marcadores") }

    Surface {
        Column {
            Spacer(modifier = Modifier.height(50.dp))

            MyOutlined(
                value = viewModel.titulo.value,
                onValueChange = { viewModel.titulo.value = it },
                label = "Titulo do Email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
            )
            MyOutlined(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                label = "Email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
            )
            MyOutlined(
                value = viewModel.mensagem.value,
                onValueChange = { viewModel.mensagem.value = it },
                label = "Mensagem",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                maxLines = 4,
                modifier = Modifier
                    .padding(5.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Divider()

            Text(text = "Selecione um marcador", modifier = Modifier.padding(15.dp))
            Button(
                onClick = { viewModel.showDialog.value = true },
                modifier = Modifier.padding(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4),
                    contentColor = Color.White
                )
            ) {
                Text(buttonText)
            }

            if (viewModel.showDialog.value) {
                AlertDialog(
                    onDismissRequest = { viewModel.showDialog.value = false },
                    title = { Text("Selecione um marcador padrão") },
                    text = {
                        Column {
                            // Talves tenho que reescrever isso aqui
                            MarcadoresEnum.entries.forEach { marcador ->
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = MaterialTheme.colorScheme.secondary
                                    ),

                                    onClick = {
                                        viewModel.selectedMarcador.value = marcador.name
                                        buttonText = marcador.name
                                        viewModel.showDialog.value = false
                                    }
                                ) {
                                    Text(marcador.name)
                                }
                            }
                            LazyColumn {
                                items(marcadorRepository.getMarcadorPersonalizado()) {
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = MaterialTheme.colorScheme.secondary
                                        ),

                                        onClick = {
                                            viewModel.selectedMarcador.value = it.nomeMarcador
                                            buttonText = it.nomeMarcador
                                            viewModel.showDialog.value = false
                                        }
                                    ) {
                                        Text(it.nomeMarcador)
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    if (viewModel.email.value.isEmpty() ||
                        viewModel.titulo.value.isEmpty() ||
                        viewModel.mensagem.value.isEmpty() ||
                        viewModel.selectedMarcador.value.isEmpty()){
                        return@Button
                    } else {

                        val enviarEmail = Email(
                            tituloEmail = viewModel.titulo.value,
                            email = viewModel.email.value,
                            mensagem = viewModel.mensagem.value,
                            marcador = viewModel.selectedMarcador.value
                        )
                        emailRepository.salvar(enviarEmail)
                        viewModel.titulo.value = ""
                        viewModel.email.value = ""
                        viewModel.mensagem.value = ""
                        viewModel.selectedMarcador.value = ""
                        buttonText = "Marcadores"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4),
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(15.dp)
            ) {
                Text(text = "Enviar")
            }

        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EnviarEmailViewModelPreview() {
    AppMailTheme {
        EnviarEmailScreen()
    }
}