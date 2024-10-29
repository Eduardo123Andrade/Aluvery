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
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.TextInput
import com.example.aluvery.ui.theme.AluveryTheme


class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList<Product>(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = { }

) {


    fun isShowSections() = searchText.isBlank()
}

@Composable
fun HomeScreen(
   products: List<Product> = emptyList()
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val sections = mapOf(
        "Todos os produtos" to products,
        "Promocões" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )


    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(searchText, ignoreCase = true) ||
                product.description?.contains(searchText, ignoreCase = false) ?: false
    }
    val searchedProducts = remember(searchText, products) {
        if (searchText.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else emptyList<Product>()
    }


    val stateHomeScreen = remember(products, searchText) {
        HomeScreenUiState(sections = sections, searchedProducts = searchedProducts, searchText = searchText, onSearchChange = {searchText = it})
    }

    HomeScreen(state = stateHomeScreen)

}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {

    AluveryTheme {
        Surface {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    TextInput(value = state.searchText, onValueChange = state.onSearchChange)
                }
                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    if (state.isShowSections()) {
                        for (section in state.sections) {
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
                        items(state.searchedProducts) { product ->
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
    HomeScreen(state = HomeScreenUiState(sampleSections))
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview(modifier: Modifier = Modifier) {
    HomeScreen(state = HomeScreenUiState(sections = sampleSections, searchText = "ham"))
}

