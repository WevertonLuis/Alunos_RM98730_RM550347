package wevertonluis.com.github.alunos_rm98730_rm550347

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DuplaGSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participantes)

        val textView = findViewById<TextView>(R.id.textParticipantes)
        textView.text = "Participantes:\n Weverton Luis - RM98730\n Thomas Abner - RM550347"

        val buttonVoltar = findViewById<Button>(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish()
        }
    }
}