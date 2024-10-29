package com.example.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.screens.HomeScreen
import com.example.aluvery.ui.theme.AluveryTheme


class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(onFabClick = {
                startActivity(
                    Intent(
                        this,
                        ProductFormActivity::class.java
                    )
                )
            }) {
                dao.products()
                val sections = mapOf(
                    "Todos os produtos" to dao.products(),
                    "Promocões" to sampleDrinks + sampleCandies,
                    "Doces" to sampleCandies,
                    "Bebidas" to sampleDrinks
                )
                HomeScreen(sections = sections)
            }
        }
    }
}

@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AluveryTheme {
        Scaffold(floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Surface {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App {
        HomeScreen(sections = sampleSections)
    }
    
}
