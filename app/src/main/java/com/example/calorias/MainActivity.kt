package com.example.calorias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calorias.ui.theme.CaloriasTheme


val alimentos = mapOf(
    "Pizza" to 800,
    "Ensalada" to 200,
    "Hamburguesa" to 700,
    "Manzana" to 100,
    "Helado" to 300,
    "Pasta" to 600,
    "Yogurt" to 150
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyConstraintLayout()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text, button1, button2, button3, button4) = createRefs()
        val startGuideLine = createGuidelineFromTop(0.05f)

        Text("Alimentos y calorías", modifier = Modifier
            .constrainAs(text){
                top.linkTo(startGuideLine)
                centerHorizontallyTo(parent)
            }.padding(16.dp)
        )

        Button(onClick = {}, modifier = Modifier.constrainAs(button1){
            top.linkTo(text.bottom)
            centerHorizontallyTo(parent)
        }){
            Text("Mostrar lista de alimentos")
        }

        Button(onClick = {}, modifier = Modifier.constrainAs(button2){
            top.linkTo(button1.bottom)
            centerHorizontallyTo(parent)
        }){
            Text("Separar los alimentos por calorías (altas y bajas)")
        }

        Button(onClick = {}, modifier = Modifier.constrainAs(button3){
            top.linkTo(button2.bottom)
            centerHorizontallyTo(parent)
        }){
            Text("Calcular Total de calorías")
        }

        Button(onClick = {}, modifier = Modifier.constrainAs(button4){
            top.linkTo(button3.bottom)
            centerHorizontallyTo(parent)
        }){
            Text("Mostrar alimentos con calorías altas")
        }
    }
}