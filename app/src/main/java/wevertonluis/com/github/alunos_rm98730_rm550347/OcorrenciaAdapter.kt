package wevertonluis.com.github.alunos_rm98730_rm550347

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OcorrenciaAdapter(private val onEventRemoved: (EventoModel) -> Unit) :
    RecyclerView.Adapter<OcorrenciaAdapter.EventoViewHolder>() {

    private var eventos = listOf<EventoModel>()

    inner class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewNomeLocal = view.findViewById<TextView>(R.id.textViewNomeLocal)
        val textViewTipo = view.findViewById<TextView>(R.id.textViewTipo)
        val textViewImpacto = view.findViewById<TextView>(R.id.textViewImpacto)
        val textViewData = view.findViewById<TextView>(R.id.textViewData)
        val textViewPessoasAfetadas = view.findViewById<TextView>(R.id.textViewPessoasAfetadas)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(evento: EventoModel) {
            textViewNomeLocal.text = "Nome do local: ${evento.nomeLocal}"
            textViewTipo.text = "Tipo: ${evento.tipo}"
            textViewImpacto.text = "Grau Impacto: ${evento.grau}"
            textViewData.text = "Data: ${evento.data}"
            textViewPessoasAfetadas.text = "Pessoas afetadas: ${evento.numero}"
            button.setOnClickListener {
                onEventRemoved(evento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evento_layout, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    fun updateEvents(newEvent: List<EventoModel>) {
        eventos = newEvent
        notifyDataSetChanged()
    }
}