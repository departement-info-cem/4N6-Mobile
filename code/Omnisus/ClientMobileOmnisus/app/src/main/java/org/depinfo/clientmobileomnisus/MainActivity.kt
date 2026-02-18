package org.depinfo.clientmobileomnisus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.depinfo.clientmobileomnisus.ui.theme.OmnisusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmnisusTheme {
                OmnisusApp()
            }
        }
    }
}

sealed class Screen {
    data object SignIn : Screen()
    data object Grade : Screen()
    data class EditUser(val publicName: String) : Screen()
}

@Composable
fun OmnisusApp() {
    var currentScreen: Screen by remember { mutableStateOf(Screen.SignIn) }

    when (val screen = currentScreen) {
        is Screen.SignIn -> SignInScreen(
            onSignInSuccess = { currentScreen = Screen.Grade }
        )
        is Screen.Grade -> GradeScreen(
            onEditUser = { publicName -> currentScreen = Screen.EditUser(publicName) }
        )
        is Screen.EditUser -> EditUserScreen(
            currentPublicName = screen.publicName,
            onSaveSuccess = { currentScreen = Screen.Grade }
        )
    }
}
