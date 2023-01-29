package ua.vitolex.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.vitolex.notesappmvvm.database.room.AppRoomDatabase
import ua.vitolex.notesappmvvm.database.room.repository.RoomRepository
import ua.vitolex.notesappmvvm.model.Note
import ua.vitolex.notesappmvvm.utils.REPOSITORY
import ua.vitolex.notesappmvvm.utils.TYPE_FIREBASE
import ua.vitolex.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    fun initDataBase(type: String, onSuccess: () -> Unit) {
        Log.d("MyLog", "MainViewModel initDatabase with type: $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}

//class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
//    override fun <T: ViewModel?> create(modelClass: Class<T>): T{
//        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
//            return  MainViewModel(application=application) as T
//        }
//        throw  IllegalArgumentException("Unknown ViewModel Class")
//    }
//}