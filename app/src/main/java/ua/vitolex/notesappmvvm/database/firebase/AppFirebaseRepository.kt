package ua.vitolex.notesappmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.ktx.Firebase
import ua.vitolex.notesappmvvm.database.DatabaseRepository
import ua.vitolex.notesappmvvm.model.Note
import ua.vitolex.notesappmvvm.utils.LOGIN
import ua.vitolex.notesappmvvm.utils.PASSWORD

class AppFirebaseRepository : DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()
    override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: ()-> Unit, onFail: (String)-> Unit) {
      mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
          .addOnSuccessListener { onSuccess() }
          .addOnFailureListener {
              mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                  .addOnSuccessListener { onSuccess() }
                  .addOnFailureListener { onFail(it.message.toString()) }
          }
    }
}