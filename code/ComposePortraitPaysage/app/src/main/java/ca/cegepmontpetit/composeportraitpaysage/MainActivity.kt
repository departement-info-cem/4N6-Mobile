package ca.cegepmontpetit.composeportraitpaysage

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onCourseClick = { course ->
                    navController.navigate("detail/${course.no}")
                }
            )
        }
        composable(
            "detail/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId")
            val course = CoursProvider.coursList.find { it.no == courseId }
            CourseDetailScreen(course)
        }
    }
}

@Composable
fun MainScreen(onCourseClick: (Cours) -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    if (isLandscape) {
        LandscapeLayout()
    } else {
        CourseListScreen(onCourseClick = onCourseClick)
    }
}

@Composable
fun LandscapeLayout() {
    var selectedCourse by remember { mutableStateOf<Cours?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            CourseListScreen(
                onCourseClick = { course ->
                    selectedCourse = course
                }
            )
        }
        Box(modifier = Modifier.weight(2f)) {
            CourseDetailScreen(selectedCourse)
        }
    }
}

@Composable
fun CourseListScreen(onCourseClick: (Cours) -> Unit) {
    LazyColumn {
        items(CoursProvider.coursList) { course ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onCourseClick(course) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = course.nom, fontWeight = FontWeight.Bold)
                    Text(text = course.no)
                }
            }
        }
    }
}

@Composable
fun CourseDetailScreen(course: Cours?) {
    if (course == null) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Sélectionnez un cours pour voir les détails.")
        }
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = course.nom, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Numéro: ${course.no}", fontSize = 18.sp)
            Text(text = "Session: ${course.session}", fontSize = 18.sp)
            Text(text = "Heures: ${course.heures ?: "N/A"}", fontSize = 18.sp)
            
            if (course.url != null) {
                Text(text = "URL: ${course.url}", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
            }
            
            Text(text = "Icônes: ${course.icons.joinToString(", ")}", fontSize = 16.sp)
        }
    }
}