package ca.cegepmontpetit.composescrollscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScrollScrollScreen()
                }
            }
        }
    }
}

data class ListItem(
    val id: Int,
    val label: String,
    val isHorizontal: Boolean = false
)

@Composable
fun ScrollScrollScreen() {
    val items = remember {
        mutableStateListOf<ListItem>().apply {
            repeat(50) { index ->
                val label = if (index == 1) "balaie moi sur le côté" else "Item $index"
                add(ListItem(index, label, index == 3))
            }
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = items, key = { it.id }) { item ->
            if (item.isHorizontal) {
                HorizontalScrollableRow()
            } else {
                SwipeableVerticalElement(
                    item = item,
                    onDismiss = { items.remove(item) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableVerticalElement(item: ListItem, onDismiss: () -> Unit) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
                onDismiss()
                true
            } else false
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            )
        },
        content = { VerticalElement(item.id, item.label) }
    )
}

@Composable
fun VerticalElement(index: Int, label: String) {
    val color = rememberRandomDarkRedColor(index)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = Color.White)
    }
}

@Composable
fun HorizontalScrollableRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        items(20) { index ->
            val color = rememberRandomDarkBlueColor(index)
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "H-Item $index", color = Color.Black)
            }
        }
    }
}

@Composable
fun rememberRandomDarkRedColor(seed: Int): Color {
    val random = Random(seed)
    return Color(
        red = random.nextInt(100, 200),
        green = random.nextInt(0, 50),
        blue = random.nextInt(0, 50),
        alpha = 255
    )
}

@Composable
fun rememberRandomDarkBlueColor(seed: Int): Color {
    val random = Random(seed + 1000)
    return Color(
        red = random.nextInt(0, 50),
        green = random.nextInt(0, 50),
        blue = random.nextInt(100, 200),
        alpha = 255
    )
}
