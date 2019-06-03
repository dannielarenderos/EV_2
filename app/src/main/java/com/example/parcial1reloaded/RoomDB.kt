package com.example.parcial1reloaded

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parcial1reloaded.dao.EquipoDao
import com.example.parcial1reloaded.dao.PartidoDao
import com.example.parcial1reloaded.entity.Equipo
import com.example.parcial1reloaded.entity.Partido
import com.example.parcial1reloaded.repository.Repository

@Database(entities = [Equipo::class, Partido::class], version = 3, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {

    abstract fun equipoDao(): EquipoDao
    abstract fun partidoDao(): PartidoDao


    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
                context: Context
        ): RoomDB {

            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this) {
                INSTANCE = Room
                        .databaseBuilder(context, RoomDB::class.java, "Database")
                        .fallbackToDestructiveMigration()
                        .build()
                return INSTANCE!!
            }

        }

    }

}