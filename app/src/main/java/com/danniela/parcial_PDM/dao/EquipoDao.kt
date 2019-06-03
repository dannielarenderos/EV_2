package com.danniela.parcial_PDM.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danniela.parcial_PDM.entity.Equipo

@Dao
interface EquipoDao {

    @Insert
    suspend fun insertEquipo(equipo: Equipo)

    @Query("SELECT eq.* FROM  equipo eq LEFT JOIN partido pa ON pa.id_partido=eq.fk_partido WHERE pa.id_partido=:idPartido")
    fun getAllEquipos(idPartido: Long): LiveData<List<Equipo>>

    @Query("DELETE FROM equipo")
    suspend fun deleteAll()
}