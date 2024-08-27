package com.wilmer.fooddataandlist.data.model

import java.time.LocalDateTime
import java.util.UUID

data class FoodList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val items: List<Int>,
    val dateOfCreation: LocalDateTime = LocalDateTime.now()
)
