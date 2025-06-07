package wevertonluis.com.github.alunos_rm98730_rm550347

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.jvm.java


class OcorrenciaViewModel(application: Application) : AndroidViewModel(application) {

    private val ocorrenciaDao: OcorrenciaDao


    val eventoLiveData: LiveData<List<EventoModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            OcorrenciaDatabase::class.java,
            "eventos_database"
        ).fallbackToDestructiveMigration()
            .build()

        ocorrenciaDao = database.eventoDao()

        eventoLiveData = ocorrenciaDao.getAll()
    }

    fun addEvent(nomeLocal: String, tipo: String, grau: String, data: String, numero: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            val newEvent = EventoModel(
                nomeLocal = nomeLocal,
                tipo = tipo,
                grau = grau,
                data = data,
                numero = numero
            )
            ocorrenciaDao.insert(newEvent)
        }
    }

    fun removeEvent(evento: EventoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            ocorrenciaDao.delete(evento)
        }
    }
}