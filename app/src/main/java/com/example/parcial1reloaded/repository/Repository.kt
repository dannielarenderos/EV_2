package com.example.parcial1reloaded.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.parcial1reloaded.dao.EquipoDao
import com.example.parcial1reloaded.dao.PartidoDao
import com.example.parcial1reloaded.entity.Equipo
import com.example.parcial1reloaded.entity.Partido

class Repository(private val equipoDao: EquipoDao, private val partidoDao: PartidoDao) {

    @WorkerThread
    suspend fun insertEquipo(equipo: Equipo) {
        equipoDao.insertEquipo(equipo)
    }


    @WorkerThread
    suspend fun insertPartido(partido: Partido) {
        partidoDao.insertPartido(partido)
    }
    fun getThisPartidoId(hora: String, fecha:String):LiveData<Long> {
       return partidoDao.getThisPartidoId(hora, fecha)
    }

    fun getAllEquipos(idPartido: Long):LiveData<List<Equipo>>{
        return equipoDao.getAllEquipos(idPartido)
    }
    fun getAllPartidos():LiveData<List<Partido>>{
        return partidoDao.getAllPartidos()
    }
}