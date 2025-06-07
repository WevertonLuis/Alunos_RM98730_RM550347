package wevertonluis.com.github.alunos_rm98730_rm550347

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface OcorrenciaDao {

    @Query("SELECT * FROM EventoModel")
    fun getAll(): LiveData<List<EventoModel>>

    @Insert
    fun insert(evento: EventoModel)

    @Delete
    fun delete(evento: EventoModel)
}