package br.com.fiap.appmail.modal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calendario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String = "",
    val data: String = "",
    val descricao: String = ""
)
