package com.example.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.ui.theme.AluveryTheme

class ProductFormActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AluveryTheme {
            }
        }
    }

    @Composable
    fun ProductFormScreen(modifier: Modifier = Modifier) {
        var url by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Criando o produto",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                )
            TextField(
                value = url,
                onValueChange = { url = it },
                placeholder = {
                    Text(text = "Url da imagem")
                },
                modifier = Modifier.fillMaxWidth(),
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(text = "Imagem")
                },
                placeholder = {
                    Text(text = "Url da imagem")
                },
                modifier = Modifier.fillMaxWidth(),

            )

            TextField(
                value = price,
                onValueChange = { price = it },
                label = {
                    Text(text = "Preço")
                },
                placeholder = {
                    Text(text = "R$ 12,99")
                },
                modifier = Modifier.fillMaxWidth(),

            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text(text = "Descricão")
                },
                placeholder = {
                    Text(text = "Descricão")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),

            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text= "Salvar")
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    private fun ProductFormScreenPreview() {
        AluveryTheme {
            Surface {
                ProductFormScreen()
            }
        }
    }
}