package br.com.fiap.appmail.database.repository

import android.content.Context
import br.com.fiap.appmail.database.dao.EmailDb
import br.com.fiap.appmail.model.MarcadorPersonalizado


class MarcadorRepository(contex: Context) {

    private val db = EmailDb.getDatabase(contex).marcadorDao()

    fun insert(marcador: MarcadorPersonalizado): Long {
        return db.insert(marcador)
    }

    fun delete(marcador: MarcadorPersonalizado): Int {
        return db.delete(marcador)
    }

    fun update(marcador: MarcadorPersonalizado): Int {
        return db.update(marcador)
    }

    fun getAllMarcadores(): List<MarcadorPersonalizado>{
        return db.getMarcadorPersonalizado()
    }

    fun getMarcadorPersonalizado(): List<MarcadorPersonalizado>{
        return db.getMarcadorPersonalizado()
    }
}