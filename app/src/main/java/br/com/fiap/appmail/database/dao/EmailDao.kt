package br.com.fiap.appmail.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.appmail.modal.Email

@Dao
interface EmailDao {

    @Insert
    fun save(email: Email): Long

    @Update
    fun update(email: Email): Int

    @Delete
    fun delete(email: Email): Int

    @Query(value = "SELECT * FROM tbl_email")
    fun getAll(): List<Email>

    @Query(value = "SELECT * FROM tbl_email WHERE email = :email")
    fun findByEmail(email: String): Email

//    @Query(value = "SELECT * FROM tbl_email WHERE marcador = :marcador")
//    fun findByMarcador(marcador: String): List<Email>
}