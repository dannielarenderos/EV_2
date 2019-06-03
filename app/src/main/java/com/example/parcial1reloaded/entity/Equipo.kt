package com.example.parcial1reloaded.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "equipo",
    foreignKeys = arrayOf(
        ForeignKey(entity = Partido::class,
            parentColumns = arrayOf("id_partido"),
            childColumns = arrayOf("fk_partido"),
            onDelete = CASCADE)))
data class Equipo (

    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "puntaje") val puntaje:String,
    @ColumnInfo(name = "isWinner") val isWinner:Boolean,
    @ColumnInfo(name = "fk_partido") var idPartido:Long
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_equipo") var id: Long=0
}