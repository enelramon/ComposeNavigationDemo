package com.sagrd.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sagrd.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavegacionDeMiApp()

                }
            }
        }
    }
}


@Composable
fun NavegacionDeMiApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Index"
    ) {
        composable("Index") {

            Index(
                onNavigate = {
                    navController.navigate("ScreenTwo")
                },
                onGoScreen4 = {
                    navController.navigate("ScreenFour")
                },
                onGoScreen5 = {
                    navController.navigate("ScreenFive")
                },
                onGoDetailScreen = {

                    navController.navigate("ScreenDetail?id=5")

                },
                onGoDetailScreenWithId = { id->

                    navController.navigate("ScreenDetail?id=${id}")

                }
            )
        }
        composable("ScreenTwo") {
            ScreenTwo(
                onNavigate = {
                    navController.navigate("ScreenThree")
                }
            )
        }
        composable(
            route = "ScreenDetail?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            ScreenDetail(
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }
        composable("ScreenThree") {
            ScreenThree(
                onNavigateHome = {
                    navController.popBackStack("Index", false)
                },
                onNavigateBack = {
                    if (navController.canNavigateBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
        composable("ScreenFour") {
            ScreenFour()
        }
        composable("ScreenFive") {
            ScreenFive()
        }

    }
}

@Composable
fun ScreenFour() {
    Column {
        Text(text = "Screen Four", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Esta es la mejor")
    }
}

@Composable
fun ScreenFive() {
    Column {
        Text(text = "Screen Five", style = MaterialTheme.typography.headlineLarge)

        Text(text = "Esta es la mejor 2")
    }
}

@Composable
fun Index(
    onNavigate: () -> Unit,
    onGoScreen4: () -> Unit,
    onGoScreen5: () -> Unit,
    onGoDetailScreen: () -> Unit,
    onGoDetailScreenWithId: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Row {

                Button(onClick = onGoScreen4) {
                    Text(text = "Ira 4")
                }
                Button(onClick = onGoScreen5) {
                    Text(text = "Ira 5")
                }
            }
            Text("Screen One", style = MaterialTheme.typography.headlineLarge)
            Text("Also this is home screen")
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { onNavigate() }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                Text(
                    text = "Go to Screen Two",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Button(
                onClick = { onGoDetailScreen() }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
                Text(
                    text = "Go to Detail With Id 5",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            //add a horizontal line

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                var id by remember { mutableIntStateOf(0) }

                OutlinedTextField(
                    label = { Text("Id") },
                    value = id.toString(),
                    onValueChange = {
                        id = it.toIntOrNull() ?: 0
                    })

                Button(
                    onClick = {
                        onGoDetailScreenWithId(id)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    Text(
                        text = "Go"
                    )
                }
            }
        }
    }

}

@Composable
fun ScreenTwo(onNavigate: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text("Screen Two", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = { onNavigate() }
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                Text(text = "Go to Screen Three")
            }
        }
    }
}

@Composable
fun ScreenThree(onNavigateHome: () -> Unit, onNavigateBack: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text("Screen Three", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(32.dp))
            Row {
                OutlinedButton(
                    onClick = { onNavigateHome() }
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    Text(text = "Go Home")
                }
                OutlinedButton(
                    onClick = { onNavigateBack() }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    Text(text = "Go Back")
                }
            }
        }
    }
}

val NavHostController.canNavigateBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED