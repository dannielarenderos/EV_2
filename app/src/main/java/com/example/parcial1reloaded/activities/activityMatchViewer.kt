package com.example.parcial1reloaded.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.parcial1reloaded.R
import com.example.parcial1reloaded.entity.dto.PartidoEquipoDTO
import kotlinx.android.synthetic.main.activity_match_viewer.*

class activityMatchViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_viewer)
        val partido: PartidoEquipoDTO? = intent.getBundleExtra("bundle").getParcelable("PARTIDO")
        if(partido!=null){ init(partido)}
    }

    private fun init(partido: PartidoEquipoDTO?) {
        with(this){
            tv_fecha.text=partido!!.fecha
            tv_equipo1.text=partido.team1
            tv_puntaje1.text=partido.team1Score
            tv_equipo2.text=partido.team2
            tv_puntaje2.text=partido.team2Score
            tv_hora.text=partido.hora
            val ganador= if(partido.team1isWinner){partido.team1}else{partido.team2}
            if(partido.team1Score==partido.team2Score){
                tv_ganador.text= "Empate!!"
            }else {
                tv_ganador.text = ganador
            }
        }
    }
}