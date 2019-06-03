package com.danniela.parcial_PDM.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danniela.parcial_PDM.entity.Partido

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