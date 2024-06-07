package br.com.fiap.appmail.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_marcador_personalizado")
data class MarcadorPersonalizado(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "nome_marcador") val nomeMarcador: String = ""
)
