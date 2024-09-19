package com.wilmer.fooddataandlist.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Tabs (val index:Int, val title:String,val icon: ImageVector){
    Lists(0,"Lists", Icons.AutoMirrored.Filled.List),
    Profile(1,"Profile",Icons.Default.Person)
}