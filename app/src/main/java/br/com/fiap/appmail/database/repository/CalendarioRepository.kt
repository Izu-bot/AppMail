package br.com.fiap.appmail.database.repository

import android.content.Context
import br.com.fiap.appmail.database.dao.EmailDb
import br.com.fiap.appmail.model.Calendario

class CalendarioRepository(context: Context) {

    private val db = EmailDb.getDatabase(context).calendarioDao()

    fun save(calendario: Calendario): Long {
        return db.insert(calendario)
    }

    fun update(calendario: Calendario): Int {
        return db.update(calendario)
    }

    fun delete(calendario: Calendario): Int {
        return db.delete(calendario)
    }

    fun getAll(): List<Calendario> {
        return db.getAll()
    }
}