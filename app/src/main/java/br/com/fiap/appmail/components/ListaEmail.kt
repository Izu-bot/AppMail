package br.com.fiap.appmail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.appmail.database.repository.EmailRepository
import br.com.fiap.appmail.model.Email

@Composable
fun ListaEmail(
    listaEmail: List<Email>
) {
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)

    LazyColumn {
        items(listaEmail) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
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
                        text = it.tituloEmail,
                        modifier = Modifier.padding(16.dp),
                        fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                    )
                    IconButton(onClick = {
                        emailRepository.delete(it)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Deletar"
                        )
                    }
                }
                Divider(
                    color = Color.Black,
                )
                Text(
                    text = it.email,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = it.mensagem,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = it.marcador.lowercase().replaceFirstChar { char -> char.uppercase() },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}