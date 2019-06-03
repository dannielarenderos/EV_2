package com.danniela.parcial_PDM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.danniela.parcial_PDM.entity.Equipo
import com.danniela.parcial_PDM.entity.Partido
import com.danniela.parcial_PDM.repository.Repository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelPartidoEquipo(context: Application): AndroidViewModel(context){
    private val repository: Repository
    init{
        val equipoDao= RoomDB.getInstance(context).equipoDao()
        val partidoDao= RoomDB.getInstance(context).partidoDao()
        repository=  Repository(equipoDao, partidoDao)
    }
    fun insertEquipo(equipo: Equipo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertEquipo(equipo)
    }
    fun insertarPartido(partido: Partido) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPartido(partido)
    }
    fun getThisPartidoId(hora: String, fecha:String): LiveData<Long> {
        return  repository.getThisPartidoId(hora, fecha)
    }
    fun getAllEquipos(idPartido: Long): LiveData<List<Equipo>>{
        return repository.getAllEquipos(idPartido)
    }
    fun getAllPartidos(): LiveData<List<Partido>> {
        return repository.getAllPartidos()
    }
}
