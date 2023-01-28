package ua.vitolex.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.vitolex.notesappmvvm.model.Note
import ua.vitolex.notesappmvvm.utils.TYPE_FIREBASE
import ua.vitolex.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application):AndroidViewModel(application) {
    val readText: MutableLiveData<List<Note>> by  lazy {
        MutableLiveData<List<Note>>()
    }
    val dbType: MutableLiveData<String> by lazy{
        MutableLiveData<String>(TYPE_ROOM)
    }

    init{
        readText.value=
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Note 1", subtitle = "Subtitle for note 1" ),
                        Note(title = "Note 2", subtitle = "Subtitle for note 2" ),
                        Note(title = "Note 3", subtitle = "Subtitle for note 3" ),
                        Note(title = "Note 4", subtitle = "Subtitle for note 4" )
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }
     fun initDataBase(type:String){
         dbType.value = type
        Log.d("MyLog", "MainViewModel initDatabase with type: $type")
    }
}

class MainViewModelFactory(private val application: Application):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
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