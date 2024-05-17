package br.com.fiap.appmail.vvm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.appmail.database.repository.EmailRepository
import br.com.fiap.appmail.modal.Email

@Composable
fun EnviarEmailScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val emailRepository = EmailRepository(context)


    var email by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var marcador by remember { mutableStateOf("") }

    Surface {
        Column {
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {newEmail -> email = newEmail },
                label = { Text(text = "Email") }
            )
            Divider()
            OutlinedTextField(
                value = titulo,
                onValueChange = { newTitulo -> titulo = newTitulo },
                label = { Text(text = "Asunto") }
            )
            Divider()
            OutlinedTextField(
                value = mensagem,
                onValueChange = { newMensagem -> mensagem = newMensagem },
                label = { Text(text = "Mensagem") }
            )
            Divider()
            OutlinedTextField(
                value = marcador,
                onValueChange = { newMarcador -> marcador = newMarcador },
                label = { Text(text = "Marcador") }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {
                if(email.isEmpty() || titulo.isEmpty() || mensagem.isEmpty() || marcador.isEmpty()){
                    return@Button
                } else {
                    val enviarEmail = Email(
                        email = email,
                        tituloEmail = titulo,
                        mensagem = mensagem,
                        marcador = marcador
                    )
                    emailRepository.salvar(enviarEmail)
                    email = ""
                    titulo = ""
                    mensagem = ""
                    marcador = ""
                }

            }) {
                Text(text = "Enviar")
            }
        }
    }

}