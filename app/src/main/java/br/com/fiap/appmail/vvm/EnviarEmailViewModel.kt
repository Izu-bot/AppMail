package br.com.fiap.appmail.vvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.fiap.appmail.modal.MarcadoresEnum

class EnviarEmailViewModel: ViewModel() {

    var email = mutableStateOf("")
    var titulo = mutableStateOf("")
    var mensagem = mutableStateOf("")
    var showDialog = mutableStateOf(false)
    var selectedMarcador = mutableStateOf<MarcadoresEnum?>(null)
}