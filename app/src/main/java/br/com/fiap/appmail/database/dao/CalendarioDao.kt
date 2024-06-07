package br.com.fiap.appmail.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.appmail.model.Calendario

@Dao
interface CalendarioDao {

    @Insert
    fun insert(calendario: Calendario): Long

    @Update
    fun update(calendario: Calendario): Int

    @Delete
    fun delete(calendario: Calendario): Int

    @Query(value = "SELECT * FROM Calendario")
    fun getAll(): List<Calendario>
}