package br.com.fiap.appmail.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_email")
data class Email(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String = "",
    @ColumnInfo(name = "titulo_email") val tituloEmail: String = "",
    val mensagem: String = "",
    val marcador: MarcadoresEnum
)