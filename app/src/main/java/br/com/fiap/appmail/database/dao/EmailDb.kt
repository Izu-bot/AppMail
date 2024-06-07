package br.com.fiap.appmail.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.appmail.model.Calendario
import br.com.fiap.appmail.model.Conversor
import br.com.fiap.appmail.model.Email
import br.com.fiap.appmail.model.MarcadorPersonalizado

@Database(entities = [Email::class, MarcadorPersonalizado::class, Calendario::class ], version = 3)
@TypeConverters(Conversor::class)
abstract class EmailDb : RoomDatabase() {

    abstract fun emailDao(): EmailDao
    abstract fun marcadorDao(): MarcadoresDao

    abstract fun calendarioDao(): CalendarioDao
    companion object {

        private lateinit var instance: EmailDb
        fun getDatabase(context: Context): EmailDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        EmailDb::class.java,
                        "email_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}