package com.example.parcial1reloaded.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.parcial1reloaded.R
import com.example.parcial1reloaded.ViewModelPartidoEquipo
import com.example.parcial1reloaded.entity.Partido
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_agregar_partido.*

class agregarPartidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_partido)
        val viewModel = ViewModelProviders.of(this).get(ViewModelPartidoEquipo::class.java)
        buttonIniciarPartido.setOnClickListener(){view->
            if(equipo1.text.isNotBlank() &&  equipo2.text.isNotBlank() && Et_fecha.text.isNotBlank()
                    && Et_hora.text.isNotBlank()
            ){
                var partido= Partido(Et_hora.text.toString(), Et_fecha.text.toString())
                var partidoBundle = Bundle()
                partidoBundle.putParcelable("partido", partido)

                viewModel.insertarPartido(partido)
                //Log.v("id", partido.id.toString())

                var intent = Intent(this, PartidoActivity::class.java)
                intent.putExtra("nombre_team1", equipo1.text.toString())
                intent.putExtra("nombre_team2", equipo2.text.toString())
                intent.putExtra("PARTIDO", partidoBundle)
                startActivity(intent)
            }else{
                Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_LONG)
                        .show()


            }
        }
    }
}
