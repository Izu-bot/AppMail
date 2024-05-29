package br.com.fiap.appmail.database.repository

import android.content.Context
import br.com.fiap.appmail.database.dao.EmailDb
import br.com.fiap.appmail.modal.Email

class EmailRepository(context: Context) {

    private val db = EmailDb.getDatabase(context).emailDao()

    fun salvar(email: Email): Long {
        return db.save(email)
    }

    fun update(email: Email): Int{
        return db.update(email)
    }

    fun delete(email:Email): Int{
        return db.delete(email)
    }

    fun getAll(): List<Email> {
        return db.getAll()
    }

    fun getEmailByEmail(email: String): List<Email> {
        return getAll().filter {
            it.email.startsWith(prefix = email, ignoreCase = true)
        }
    }

    fun findByMarcador(marcador: String): List<Email> {
        return db.findByMarcador(marcador)
    }
}