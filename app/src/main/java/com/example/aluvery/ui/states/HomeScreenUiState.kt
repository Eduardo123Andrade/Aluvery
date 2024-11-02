package com.example.aluvery.ui.states

import com.example.aluvery.model.Product

data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList<Product>(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = { }

) {


    fun isShowSections() = searchText.isBlank()
}
