package ua.vitolex.notesappmvvm.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ua.vitolex.notesappmvvm.model.Note

@Dao
interface NoteRoomDao {
    @Query(value = "SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note:Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)
}