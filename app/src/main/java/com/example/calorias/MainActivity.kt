package com.example.calorias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.calorias.ui.theme.CaloriasTheme


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
    val alimentos = mapOf(
        "Pizza" to 800,
        "Ensalada" to 200,
        "Hamburguesa" to 700,
        "Manzana" to 100,
        "Helado" to 300,
        "Pasta" to 600,
        "Yogurt" to 150
    )

    var listAlimentos1 by remember { mutableStateOf(alimentos.toList()) }
    var listAlimentos2 by remember { mutableStateOf<List<Pair<String, Int>>>(emptyList()) }
    var totalCalorias by remember { mutableStateOf("Alimentos y Calorías") }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text1, text2, text3, button1, button2, button3, button4) = createRefs()
        val startGuideLine = createGuidelineFromTop(0.05f)

        Text(
            totalCalorias, modifier = Modifier
                .constrainAs(text1) {
                    top.linkTo(startGuideLine)
                    centerHorizontallyTo(parent)
                }
                .padding(16.dp)
        )

        Button(onClick = {
            listAlimentos1 = alimentos.toList()
            listAlimentos2 = emptyList()
        }, modifier = Modifier.constrainAs(button1) {
            top.linkTo(text1.bottom)
            centerHorizontallyTo(parent)
        }) {
            Text("Mostrar lista de alimentos")
        }

        Button(onClick = {
            listAlimentos1 = alimentos.filter { it.value < 500 }.toList()
            listAlimentos2 = alimentos.filter { it.value >= 500 }.toList()

        }, modifier = Modifier.constrainAs(button2) {
            top.linkTo(button1.bottom)
            centerHorizontallyTo(parent)
        }) {
            Text("Separar los alimentos por calorías (altas y bajas)")
        }

        Button(onClick = {
            val totalCal = alimentos.values.sum()
            totalCalorias = "Total de calorías: $totalCal"
        }, modifier = Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom)
            centerHorizontallyTo(parent)
        }) {
            Text("Calcular Total de calorías")
        }

        Button(onClick = {
            listAlimentos1 = alimentos.filter { it.value > 500 }.toList()
            listAlimentos2 = emptyList()
        }, modifier = Modifier.constrainAs(button4) {
            top.linkTo(button3.bottom)
            centerHorizontallyTo(parent)
        }) {
            Text("Mostrar alimentos con calorías altas")
        }
        LazyColumn(
            modifier = Modifier
                .constrainAs(text2) {
                    top.linkTo(button4.bottom)
                    start.linkTo(parent.start)
                }
                .padding(vertical = 64.dp, horizontal = 16.dp)
                .width(150.dp)
        ) {
            items(listAlimentos1.toList()) { (alimento, calorias) ->
                Text(text = "$alimento: $calorias calorías")
            }
        }
        LazyColumn(
            modifier = Modifier
                .constrainAs(text3) {
                    top.linkTo(button4.bottom)
                    end.linkTo(parent.end)
                }
                .padding(vertical = 64.dp, horizontal = 16.dp)
                .width(150.dp)
        ) {
            if (listAlimentos2.isEmpty()) {
                items(listOf(Pair("", ""))) { (alimento, calorias) ->
                    Text(text = "")
                }
            } else {
                items(listAlimentos2.toList()) { (alimento, calorias) ->
                    Text(text = "$alimento: $calorias calorías")
                }
            }
        }
    }
}