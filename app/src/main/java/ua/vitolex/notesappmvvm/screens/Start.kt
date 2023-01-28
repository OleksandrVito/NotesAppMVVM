package ua.vitolex.notesappmvvm.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ua.vitolex.notesappmvvm.navigation.NavRoute
import ua.vitolex.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will we use?")
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 8.dp),
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                }) {
                Text(text = "Room database")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = 8.dp),
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                }) {
                Text(text = "Firebase database")
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesAppMVVMTheme() {
        StartScreen(navController = rememberNavController())
    }
}