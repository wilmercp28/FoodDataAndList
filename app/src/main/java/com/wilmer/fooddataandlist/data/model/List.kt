package com.wilmer.fooddataandlist.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime
import java.util.UUID
import kotlin.collections.List

data class ShoppingList(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val icon: ImageVector = Icons.Default.ShoppingCart,
    val items: List<Item>
)

data class Item(
    val id: Long,
    val quantity: String,
){

    fun changeQuantity(newQuantity: String): Item{
        return this.copy(quantity = newQuantity)
    }

}
