package br.com.fiap.appmail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.appmail.modal.Email

@Composable
fun ListaEmail(
    modifier: Modifier = Modifier,
    listaEmail: List<Email>,
) {

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
                Text(
                    text = it.tituloEmail,
                    modifier = Modifier.padding(16.dp),
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle
                )
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
                    text = it.marcador.toString(),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}