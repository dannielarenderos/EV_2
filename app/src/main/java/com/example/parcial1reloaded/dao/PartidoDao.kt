package com.example.parcial1reloaded.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.parcial1reloaded.entity.Partido
import com.example.parcial1reloaded.entity.dto.PartidoEquipoDTO

@Dao
interface PartidoDao {

    @Insert
    suspend fun insertPartido(partido: Partido)

    @Query("SELECT id_partido from partido WHERE hora=:hora AND fecha=:fecha")
    fun getThisPartidoId(hora:String, fecha:String): LiveData<Long>

    @Query("SELECT * FROM partido")
    fun getAllPartidos(): LiveData<List<Partido>>

    @Query("DELETE FROM partido")
    suspend fun deleteAll()
}