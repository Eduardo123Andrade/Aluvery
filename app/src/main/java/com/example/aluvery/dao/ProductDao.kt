package com.example.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.example.aluvery.model.Product
import com.example.aluvery.sampledata.sampleProducts

class ProductDao {
    companion object {
//        private val products = mutableStateListOf<Product>(*sampleProducts.toTypedArray())
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}