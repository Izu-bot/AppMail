package br.com.fiap.appmail.vvm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EnviarEmailViewModel: ViewModel() {

    var email = mutableStateOf("")
    var titulo = mutableStateOf("")
    var mensagem = mutableStateOf("")
    var showDialog = mutableStateOf(false)
    var selectedMarcador = mutableStateOf("")
}