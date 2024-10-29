package com.example.aluvery.ui.activities

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.aluvery.R
import com.example.aluvery.model.Product
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AluveryTheme {
                ProductFormScreen()
            }
        }
    }

    @Composable
    fun ProductFormScreen(modifier: Modifier = Modifier) {
        var url by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        val formatter = remember {
            DecimalFormat("#.##")
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier)
            Text(
                text = "Criando o produto",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
            )

            if (url.isNotBlank()) {
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder),
                )
            }

            TextField(
                value = url,
                onValueChange = { url = it },
                placeholder = {
                    Text(text = "Url da imagem")
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next,
                )
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(text = "Nome")
                },
                placeholder = {
                    Text(text = "Nome")
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words
                )
            )

            TextField(
                value = price,
                onValueChange = {
                    try{
                        price = formatter.format(BigDecimal(it))
                    }catch (e: IllegalArgumentException) {
                        if(it.isNotBlank()) {
                            price = it
                        }
                    }
                },
                label = {
                    Text(text = "Preço")
                },
                placeholder = {
                    Text(text = "R$ 12,99")
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),

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
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Button(
                onClick = {
                    val product = Product(
                        name = name,
                        price = BigDecimal(price),
                        image = url,
                        description = description
                    )
                    Log.i("PRODUCT", product.toString())
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Salvar")
            }
            Spacer(modifier = Modifier)
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