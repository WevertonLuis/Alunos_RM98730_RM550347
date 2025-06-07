package wevertonluis.com.github.alunos_rm98730_rm550347

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EventoModel::class], version = 1)
abstract class OcorrenciaDatabase : RoomDatabase() {
    abstract fun eventoDao(): OcorrenciaDao
}