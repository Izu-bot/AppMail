package br.com.fiap.appmail.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calendario(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nome: String = "",
    val data: String = "",
    val descricao: String = ""
)
