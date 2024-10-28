package com.example.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.model.Product
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.TextInput
import com.example.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchTex: String = "",
) {
    var text by remember {
        mutableStateOf(searchTex)
    }

    val searchedProducts = remember(text) {
        sampleProducts.filter { product ->
            product.name.contains(text, ignoreCase = true) ||
                    product.description?.contains(text, ignoreCase = false) ?: false
        }
    }

    AluveryTheme {
        Surface {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    TextInput(value = text, onValueChange = { text = it })
                }
                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    if (text.isBlank()) {
                        for (section in sections) {
                            val title = section.key
                            val products = section.value
                            item {
                                ProductsSection(
                                    title = title,
                                    products = products
                                )
                            }
                        }
                    } else {
                        items(searchedProducts) { product ->
                            CardProductItem(
                                product = product,
                                Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }

                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(sampleSections)
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview(modifier: Modifier = Modifier) {
    HomeScreen(sampleSections, "ham")
}

