package com.danniela.parcial_PDM.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.danniela.parcial_PDM.dao.EquipoDao
import com.danniela.parcial_PDM.dao.PartidoDao
import com.danniela.parcial_PDM.entity.Equipo
import com.danniela.parcial_PDM.entity.Partido

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