package br.com.fiap.appmail.model

import androidx.room.TypeConverter

class Conversor {

    @TypeConverter
    fun fromMarcador(value: MarcadoresEnum): String{
        return value.name
    }

    @TypeConverter
    fun toMacador(value: String): MarcadoresEnum{
        return MarcadoresEnum.valueOf(value)
    }
}