package br.com.fiap.appmail.vvm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.appmail.components.BarraPesquisa
import br.com.fiap.appmail.ui.theme.AppMailTheme

@Composable
fun HomeScreen(navController: NavController) {
    Spacer(modifier = Modifier.height(40.dp))

    val viewModel = viewModel<HomeScreenViewModel>()

    Column {
        BarraPesquisa(
            value = viewModel.searchText.value,
            onValueChange = { viewModel.searchText.value = it },
            placeholder = "Pesquisar Email",
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        Button(onClick = { navController.navigate("tela") }) {
            Text(text = "Outra tela")
        }
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppMailTheme {
//        HomeScreen()
    }
}