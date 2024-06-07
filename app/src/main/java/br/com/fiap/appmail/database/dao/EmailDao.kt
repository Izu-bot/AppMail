package br.com.fiap.appmail.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.appmail.model.Email

@Dao
interface EmailDao {

    @Insert
    fun save(email: Email): Long

    @Update
    fun update(email: Email): Int

    @Delete
    fun delete(email: Email): Int

    @Query(value = "SELECT * FROM tbl_email ORDER BY id DESC")
    fun getAll(): List<Email>

    @Query(value = "SELECT * FROM tbl_email WHERE marcador = :marcador")
    fun findByMarcador(marcador: String): List<Email>

    @Query(value = "SELECT DISTINCT marcador FROM tbl_email")
    fun getMarcador(): List<String>
}