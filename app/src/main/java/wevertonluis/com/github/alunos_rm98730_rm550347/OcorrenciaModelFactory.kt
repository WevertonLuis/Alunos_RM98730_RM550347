package wevertonluis.com.github.alunos_rm98730_rm550347

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.jvm.java


class OcorrenciaModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OcorrenciaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OcorrenciaViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}