package br.com.fiap.appmail.vvm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.appmail.components.BarraPesquisa
import br.com.fiap.appmail.components.ListaEmail
import br.com.fiap.appmail.database.repository.EmailRepository
import br.com.fiap.appmail.ui.theme.AppMailTheme


@Composable
fun HomeScreen(navController: NavController) {
    Spacer(modifier = Modifier.height(40.dp))
    val context = LocalContext.current
    val emailRepository = EmailRepository(context)
    val viewModel: HomeScreenViewModel = viewModel()

    var listaEmail by remember { mutableStateOf(emailRepository.getEmailByEmail(viewModel.searchText.value)) }


    Column {
        Spacer(modifier = Modifier.height(10.dp))
        BarraPesquisa(
            value = viewModel.searchText.value,
            onValueChange = { email -> viewModel.searchText.value = email },
            placeholder = "Pesquisar Email",
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            ),
            trailingIcon = {
                IconButton(onClick = {
                    listaEmail = emailRepository.getEmailByEmail(viewModel.searchText.value)
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar",
                        tint = Color.White
                    )
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                IconButton(onClick = { listaEmail = emailRepository.getAll() }
                )
                {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            items(emailRepository.getMarcador()) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5E5E5E),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {
                        listaEmail = emailRepository.findByMarcador(it)
                    }) {
                    Text(text = it.lowercase().replaceFirstChar { char -> char.uppercase() })
                }
            }
        }
        ListaEmail(listaEmail = listaEmail)
    }


}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppMailTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}