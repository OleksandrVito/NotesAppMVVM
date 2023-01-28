package ua.vitolex.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.vitolex.notesappmvvm.database.room.AppRoomDatabase
import ua.vitolex.notesappmvvm.database.room.repository.RoomRepository
import ua.vitolex.notesappmvvm.model.Note
import ua.vitolex.notesappmvvm.utils.REPOSITORY
import ua.vitolex.notesappmvvm.utils.TYPE_FIREBASE
import ua.vitolex.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application):AndroidViewModel(application) {
val context = application

     fun initDataBase(type:String, onSuccess:()->Unit){
        Log.d("MyLog", "MainViewModel initDatabase with type: $type")
         when(type){
             TYPE_ROOM -> {
                 val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                 REPOSITORY = RoomRepository(dao)
                 onSuccess()
             }
         }
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