package br.com.willrpg.projetoAlcoolOuGasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.willrpg.projetoAlcoolOuGasolina.ui.theme.ProjetoAlcoolOuGasolinaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetoAlcoolOuGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    App()
                }
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App() {
    val keyboardController = LocalSoftwareKeyboardController.current

    var valorGasolina by remember { mutableStateOf("") };

    var valorAlcool by remember { mutableStateOf("") };

    val fieldsNotBlank = valorGasolina.isNotBlank() && valorAlcool.isNotBlank();

    Column(
        Modifier
            .background(color = Color(0xFFFF5722))
            .fillMaxSize()
            .clickable {
                keyboardController?.hide()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Álcool ou Gasolina", style = TextStyle(
                    color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold
                )
            );

            AnimatedVisibility(visible = fieldsNotBlank) {
                if (fieldsNotBlank) {
                    val isGasoline = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7

                    val alcoholOrGasoline = if (isGasoline) {
                        "Gasolina"
                    } else {
                        "Alcool"
                    }

                    val colorDynamic = if (isGasoline) {
                        Color.Yellow
                    } else {
                        Color.Black
                    }

                    Text(
                        text = alcoholOrGasoline, style = TextStyle(
                            color = colorDynamic, fontSize = 42.sp, fontWeight = FontWeight.Bold
                        )
                    );
                }
            }

            TextField(value = valorGasolina, onValueChange = {
                valorGasolina = it
            }, label = {
                Text(text = "Gasolina (preço por litro)")
            }, keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number // Define o tipo de teclado como numérico
            )
            );

            TextField(
                value = valorAlcool, onValueChange = {
                    valorAlcool = it
                },

                label = {
                    Text(text = "Álcool (preço por litro)")
                }, keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number // Define o tipo de teclado como numérico
                )
            );
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjetoAlcoolOuGasolinaTheme {
        App()
    }
}