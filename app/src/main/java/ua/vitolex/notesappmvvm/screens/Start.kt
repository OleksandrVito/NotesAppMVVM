package ua.vitolex.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ua.vitolex.notesappmvvm.MainViewModel
import ua.vitolex.notesappmvvm.MainViewModelFactory
import ua.vitolex.notesappmvvm.navigation.NavRoute
import ua.vitolex.notesappmvvm.ui.theme.NotesAppMVVMTheme
import ua.vitolex.notesappmvvm.utils.*
import ua.vitolex.notesappmvvm.utils.Constants.Keys.FIREBASE_DATABASE
import ua.vitolex.notesappmvvm.utils.Constants.Keys.ROOM_DATABASE
import ua.vitolex.notesappmvvm.utils.Constants.Keys.WHAT_WILL_WE_USE

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var login by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }
    var password by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = Constants.Keys.LOG_IN,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDataBase(TYPE_FIREBASE) {
                                DB_TYPE.value= TYPE_FIREBASE
                                navController.navigate(NavRoute.Main.route)
                            }
                            Log.d("MyLog", "meeeesss")
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = Constants.Keys.SIGN_IN)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = WHAT_WILL_WE_USE)
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(vertical = 8.dp),
                    onClick = {
                        viewModel.initDataBase(TYPE_ROOM) {
                            DB_TYPE.value= TYPE_ROOM
                            navController.navigate(route = NavRoute.Main.route)
                        }

                    }) {
                    Text(text = ROOM_DATABASE)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(vertical = 8.dp),
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }

                    }) {
                    Text(text = FIREBASE_DATABASE)
                }


            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesAppMVVMTheme() {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}