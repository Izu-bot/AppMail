package br.com.fiap.appmail.vvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel: ViewModel() {
    val searchText = mutableStateOf("")
}