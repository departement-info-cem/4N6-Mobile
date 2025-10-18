package ca.cem.proportions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.cem.proportions.ui.theme.ProportionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProportionsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                        // Si la somme des poids donne 100, c'est comme des pourcentages
                        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .weight(88f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF90CAF9)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Si je contrains la hauteur et la largeur, des fois ça ne rentre pas", color = Color.White) }
                            Box(
                                modifier = Modifier
                                    .weight(12f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF1565C0)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Si je contrains la hauteur et la largeur, des fois ça ne rentre pas", color = Color.White) }
                        }
                        // Cette Row a une hauteur explicite de 100dp
                        // ces 100 pixels sont alloués AVANT le calcul des poids
                        Row(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFA5D6A7)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Ce texte prend de l'espace.", color = Color.Black) }
                            Box(
                                modifier = Modifier
                                    .weight(3f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF2E7D32)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "On me donne 300dp en hauteur avant le calcul des poids", color = Color.White) }
                        }
                        // Row 6 - bleus (long text)
                        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF90CAF9)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Ce texte prend de l'espace.", color = Color.White) }
                            Box(
                                modifier = Modifier
                                    .weight(3f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF1565C0)),
                                contentAlignment = Alignment.Center
                            ) {Text(text = "Ce texte prend de l'espace.", color = Color.White) }
                        }

                        // Row 7 - rouges (Ce)
                        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .weight(.54f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFEF9A9A)),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    modifier = Modifier.width(200.dp).height(27.dp),
                                    onClick = { /*TODO*/ }
                                ){Text("Ne pas limiter la hauteur de mon bouton")}
                            }
                            Box(
                                modifier = Modifier
                                    .weight(.46f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFB71C1C)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "On peut mettre des poids de .46 tout marche", color = Color.White) }
                        }
                        // Poids de 3 donc 3 fois plus haut
                        Row(modifier = Modifier.fillMaxWidth().weight(3f)) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF90CAF9)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Mon poids de 3 me rend plus haut", color = Color.White)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(3f)
                                    .fillMaxHeight()
                                    .background(Color(0xFF1565C0)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Ce texte prend", color = Color.White)
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFEF9A9A)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Ce texte prend de l'espace.", color = Color.White) }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFB71C1C)),
                                contentAlignment = Alignment.Center
                            ) { Text(text = "Ce texte prend de l'espace.", color = Color.White) }
                        }
                    }
                }
            }
        }
    }
}
