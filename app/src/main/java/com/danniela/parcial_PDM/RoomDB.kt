package com.danniela.parcial_PDM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danniela.parcial_PDM.dao.EquipoDao
import com.danniela.parcial_PDM.dao.PartidoDao
import com.danniela.parcial_PDM.entity.Equipo
import com.danniela.parcial_PDM.entity.Partido

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