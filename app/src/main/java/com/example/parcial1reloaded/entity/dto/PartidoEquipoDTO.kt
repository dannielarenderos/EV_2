package com.example.parcial1reloaded.entity.dto

import android.os.Parcel
import android.os.Parcelable

class PartidoEquipoDTO(
        var hora:String,
        var fecha:String,
        var team1:String,
        var team2:String,
        var team1isWinner:Boolean,
        var team1Score:String,
        var team2Score:String
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(hora)
        parcel.writeString(fecha)
        parcel.writeString(team1)
        parcel.writeString(team2)
        parcel.writeByte(if (team1isWinner) 1 else 0)
        parcel.writeString(team1Score)
        parcel.writeString(team2Score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PartidoEquipoDTO> {
        override fun createFromParcel(parcel: Parcel): PartidoEquipoDTO {
            return PartidoEquipoDTO(parcel)
        }

        override fun newArray(size: Int): Array<PartidoEquipoDTO?> {
            return arrayOfNulls(size)
        }
    }
}