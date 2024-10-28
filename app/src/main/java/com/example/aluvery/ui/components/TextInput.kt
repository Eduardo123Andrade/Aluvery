package com.example.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TextInput(
    value: String,
    onValueChange: (str: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = "O que você procura?") },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        label = { Text(text = "Produto") }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }

    TextInput(text, onValueChange = { value -> text = value })
}