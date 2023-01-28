package ua.vitolex.notesappmvvm.database.room.repository

import androidx.lifecycle.LiveData
import ua.vitolex.notesappmvvm.database.DatabaseRepository
import ua.vitolex.notesappmvvm.database.room.dao.NoteRoomDao
import ua.vitolex.notesappmvvm.model.Note

class RoomRepository(private val noteRoomDao:NoteRoomDao):DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
       noteRoomDao.addNote(note = note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)

    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)

    }
}