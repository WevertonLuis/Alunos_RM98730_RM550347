package wevertonluis.com.github.alunos_rm98730_rm550347

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: OcorrenciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Registro de Eventos Extremos"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val eventsAdapter = OcorrenciaAdapter { evento ->
            viewModel.removeEvent(evento)
        }
        recyclerView.adapter = eventsAdapter

        val button = findViewById<Button>(R.id.button)
        val editName = findViewById<EditText>(R.id.editName)
        val spinnerTipo = findViewById<Spinner>(R.id.editTipo)
        val spinnerImpacto = findViewById<Spinner>(R.id.editImpacto)
        val editData = findViewById<EditText>(R.id.editData)
        val editPessoasAfetadas = findViewById<EditText>(R.id.editPessoasAfetadas)

        // Configurar DatePickerDialog para editData
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        editData.setOnClickListener {
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                editData.setText(selectedDate)
            }, year, month, day).show()
        }

        button.setOnClickListener {
            var isValid = true

            if (editName.text.isEmpty()) {
                editName.error = "Nome do local é obrigatório"
                isValid = false
            }

            val tipoSelecionado = spinnerTipo.selectedItem.toString()
            if (spinnerTipo.selectedItemPosition == 0) {
                Toast.makeText(this, "Selecione um tipo de evento válido", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            val impactoSelecionado = spinnerImpacto.selectedItem.toString()
            if (spinnerImpacto.selectedItemPosition == 0) {
                Toast.makeText(this, "Selecione um grau de impacto válido", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (editData.text.isEmpty()) {
                editData.error = "Data do evento é obrigatória"
                isValid = false
            }

            val numeroPessoasAfetadas = editPessoasAfetadas.text.toString().toIntOrNull()
            if (editPessoasAfetadas.text.isEmpty()) {
                editPessoasAfetadas.error = "Número de pessoas afetadas é obrigatório"
                isValid = false
            } else if (numeroPessoasAfetadas == null || numeroPessoasAfetadas <= 0) {
                editPessoasAfetadas.error = "Informe um número válido maior que 0"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            viewModel.addEvent(
                editName.text.toString(),
                tipoSelecionado,
                impactoSelecionado,
                editData.text.toString(),
                (numeroPessoasAfetadas ?: 0).toString()
            )

            editName.text.clear()
            spinnerTipo.setSelection(0)
            spinnerImpacto.setSelection(0)
            editData.text.clear()
            editPessoasAfetadas.text.clear()
        }

        val viewModelFactory = OcorrenciaModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OcorrenciaViewModel::class.java)

        viewModel.eventoLiveData.observe(this) { eventos ->
            eventsAdapter.updateEvents(eventos)
        }

        val buttonParticipantes = findViewById<Button>(R.id.ver_participantes_button)
        buttonParticipantes.setOnClickListener {
            val intent = Intent(this, DuplaGSActivity::class.java)
            startActivity(intent)
        }
    }
}