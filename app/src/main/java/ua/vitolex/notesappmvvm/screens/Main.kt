package ua.vitolex.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ua.vitolex.notesappmvvm.MainViewModel
import ua.vitolex.notesappmvvm.MainViewModelFactory
import ua.vitolex.notesappmvvm.model.Note
import ua.vitolex.notesappmvvm.navigation.NavRoute
import ua.vitolex.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.Black,
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icon",
                    tint = Color.Gray
                )
            }
        }
    ) {
//        Column() {
//            NoteItem("New Note 1", "TestText", navController)
//            NoteItem("New Note 2", "TestText", navController)
//            NoteItem("New Note 3", "TestText", navController)
//            NoteItem("New Note 4", "TestText", navController)
//            NoteItem("New Note 5", "TestText", navController)
//        }
//        LazyColumn {
//         items(notes) {note ->
//             NoteItem(note = note, navController = navController)
//         }
//        }

    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = note.subtitle)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    NotesAppMVVMTheme() {
        MainScreen(navController = rememberNavController())
    }
}