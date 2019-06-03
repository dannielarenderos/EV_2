package com.example.parcial1reloaded.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.parcial1reloaded.R
import com.example.parcial1reloaded.ViewModelPartidoEquipo
import com.example.parcial1reloaded.entity.Equipo
import com.example.parcial1reloaded.entity.Partido
import kotlinx.android.synthetic.main.activity_partido.*

class PartidoActivity : AppCompatActivity() {
    var idPartido:Long=0
    lateinit var nombreTeam1:String
    lateinit var nombreTeam2:String
    lateinit var hora: String
    lateinit var fecha: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partido)
        //val idPartido = intent.getStringExtra("idPartido")

        val partido: Partido? = intent.getBundleExtra("PARTIDO").getParcelable("partido")
        if(partido!=null) {
            hora = partido.hora
            fecha = partido.fecha
        }
        nombreTeam1=intent.getStringExtra("nombre_team1")
        nombreTeam2=intent.getStringExtra("nombre_team2")
        tv_fecha.text=fecha
        tv_hora.text=hora
        tv_team1.text=nombreTeam1
        tv_team2.text=nombreTeam2
        val scoreTeam1 = ViewModelProviders.of(this).get(contadorViewModel::class.java)
        val scoreTeam2 = ViewModelProviders.of(this).get(contadorViewModel::class.java)
        val viewModelPartidoEquipo = ViewModelProviders.of(this).get(ViewModelPartidoEquipo::class.java)

        viewModelPartidoEquipo.getThisPartidoId(hora,fecha).observe(this, Observer {id->
            idPartido=id
        })


        val scoreObserver1 = Observer<Int> { newInt ->
            // Update the UI, in this case, a TextView.
            tv_contTeam1.text = newInt.toString()
        }
        val scoreObserver2 = Observer<Int> { newInt ->
            // Update the UI, in this case, a TextView.
            tv_contTeam2.text = newInt.toString()
        }
        scoreTeam1.contador1.observe(this, scoreObserver1)
        scoreTeam2.contador2.observe(this, scoreObserver2)

        btn_Tres_team1.setOnClickListener(){
            scoreTeam1.anotarA(3)
        }
        btn_Dos_team1.setOnClickListener(){
            scoreTeam1.anotarA(2)
        }
        btn_Uno_team1.setOnClickListener(){
            scoreTeam1.anotarA(1)
        }
        btn_Tres_team2.setOnClickListener(){
            scoreTeam2.anotarB(3)
        }
        btn_Dos_team2.setOnClickListener(){
            scoreTeam2.anotarB(2)
        }
        btn_Uno_team2.setOnClickListener(){
            scoreTeam2.anotarB(1)
        }
        bt_finalizarPartido.setOnClickListener() {
            Log.v("id", idPartido.toString())
            val flag= tv_contTeam1.text.toString().toLong()>= tv_contTeam2.text.toString().toLong()
            var equipo1= Equipo(nombreTeam1, tv_contTeam1.text.toString(), flag, idPartido)
            var equipo2= Equipo(nombreTeam2, tv_contTeam2.text.toString(), !flag, idPartido)
            viewModelPartidoEquipo.insertEquipo(equipo1)
            viewModelPartidoEquipo.insertEquipo(equipo2)
            startActivity(Intent(this@PartidoActivity,MainActivity::class.java))
        }

    }

    class contadorViewModel : ViewModel() {
        var aux1=0
        var aux2=0

        val contador1 = MutableLiveData<Int>()
        val contador2 = MutableLiveData<Int>()

        fun anotarA(i: Int) {
            aux1= aux1+i
            contador1.postValue(aux1)
        }
        fun anotarB(i: Int) {
            aux2= aux2+i
            contador2.postValue(aux2)
        }
    }
}
