package com.danniela.parcial_PDM.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danniela.parcial_PDM.R
import com.danniela.parcial_PDM.entity.dto.PartidoEquipoDTO
import kotlinx.android.synthetic.main.cardviewpartido.view.*


class PartidoAdapter internal  constructor(context: Context, val clickListener:(PartidoEquipoDTO)->Unit ) : RecyclerView.Adapter<PartidoAdapter.ViewHolder>() {
    private var partidos: ArrayList<PartidoEquipoDTO> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardviewpartido, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = partidos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(partidos[position], clickListener)

    internal fun setPartidos(partidos: ArrayList<PartidoEquipoDTO>) {
        this.partidos = partidos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(partido: PartidoEquipoDTO, clickListener: (PartidoEquipoDTO) -> Unit) = with(itemView) {
            tv_name_equipo1.text=partido.team1
            tv_name_equipo2.text=partido.team2


            this.setOnClickListener(){
                clickListener(partido)
            }
        }
    }
}