package com.example.aluvery.ui.viewmodels

import android.icu.text.DecimalFormat
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.model.Product
import com.example.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

val formatter = DecimalFormat("#.##")


class ProductFormScreenViewModel : ViewModel() {
    private val dao = ProductDao()
    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(url = it)
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(name = it)
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(description = it)
                },
                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }
                    price?.let {
                        _uiState.value = _uiState.value.copy(price = price)
                    }
                },
            )
        }
    }


    fun save() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = name,
                image = url,
                price = convertedPrice,
                description = description
            )
            dao.save(product)
        }
    }
}