package com.example.parcial1reloaded.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial1reloaded.R
import com.example.parcial1reloaded.ViewModelPartidoEquipo
import com.example.parcial1reloaded.adapters.PartidoAdapter
import com.example.parcial1reloaded.entity.dto.PartidoEquipoDTO

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaDTO: ArrayList<PartidoEquipoDTO> = ArrayList()
    lateinit var partidoAdapter: PartidoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModelPartidoEquipo = ViewModelProviders.of(this).get(ViewModelPartidoEquipo::class.java)
        partidoAdapter = PartidoAdapter(this, {partido:PartidoEquipoDTO-> iniciarActivityPartido(partido)})
        rvPartidos.apply {
            adapter = partidoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModelPartidoEquipo.getAllPartidos().observe(this, Observer { listaPartidos ->
            if (listaPartidos.size != 0) {
                listaDTO.clear()
                listaPartidos.forEach { partido ->
                    viewModelPartidoEquipo.getAllEquipos(partido.id).observe(this@MainActivity, Observer { listaEquipos ->
                        if (listaEquipos.size >= 2) {
                            listaDTO.add(PartidoEquipoDTO(partido.hora, partido.fecha, listaEquipos[0].name, listaEquipos[1].name, listaEquipos[0].isWinner, listaEquipos[0].puntaje, listaEquipos[1].puntaje))
                        }
                    })
                }
                partidoAdapter.setPartidos(listaDTO)
            }
        })
        fab.setOnClickListener() {
            val intent = Intent(this, agregarPartidoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarActivityPartido(partido: PartidoEquipoDTO) {
        val partidoBundle = Bundle()
        partidoBundle.putParcelable("PARTIDO", partido)
        startActivity(Intent(this, activityMatchViewer::class.java).putExtra("bundle", partidoBundle))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
