package com.wilmer.fooddataandlist.data.model

import java.time.LocalDateTime
import java.util.UUID
import kotlin.collections.List

data class ShoppingList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val items: List<Item>
){
    fun addItem(item: Item): ShoppingList{
        return this.copy(items = this.items + item)
    }
    fun removeItem(item: Item): ShoppingList{
        return this.copy(items = this.items - item)
    }
    fun changeName(newName: String): ShoppingList{
        return this.copy(name = newName)
    }
}

data class Item(
    val id: Long,
    val quantity: String,
){

    fun changeQuantity(newQuantity: String): Item{
        return this.copy(quantity = newQuantity)
    }

}
