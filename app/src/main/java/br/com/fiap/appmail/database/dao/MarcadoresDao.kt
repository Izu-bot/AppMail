package br.com.fiap.appmail.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.appmail.modal.MarcadorPersonalizado

@Dao
interface MarcadoresDao {

    @Insert
    fun insert(marcador: MarcadorPersonalizado): Long

    @Delete
    fun delete(marcador: MarcadorPersonalizado): Int

    @Update
    fun update(marcador: MarcadorPersonalizado): Int

    @Query(value = "SELECT DISTINCT nome_marcador FROM tbl_marcador_personalizado")
    fun getMarcadorPersonalizado(): List<String>
}