package com.wilmer.fooddataandlist.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBarSimple(
    textField: String,
    onTextFieldChange: (String) -> Unit,
    onSearchClick: () -> Unit
){

    TextField(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        value = textField,
        onValueChange = { onTextFieldChange(it) },
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )





}