package br.com.fiap.appmail.vvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeScreenViewModel: ViewModel() {
    val searchText = mutableStateOf("")
}