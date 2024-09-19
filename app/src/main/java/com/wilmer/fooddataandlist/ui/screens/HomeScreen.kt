package com.wilmer.fooddataandlist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wilmer.fooddataandlist.data.mock.getMockShoppingList
import com.wilmer.fooddataandlist.data.mock.getMockViewModel
import com.wilmer.fooddataandlist.data.model.Tabs
import com.wilmer.fooddataandlist.ui.components.ShoppingListView
import com.wilmer.fooddataandlist.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: FoodViewModel, navController: NavHostController) {
    val shoppingLists by viewModel.shoppingLists.collectAsState()
    var selectedTab by remember { mutableStateOf(Tabs.Lists) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = selectedTab.title) })
        },
        bottomBar = {
            BottomAppBar {
                TabRow(
                    containerColor = Color.Transparent,
                    selectedTabIndex = selectedTab.index){
                    Tabs.entries.forEach { tab ->
                        Tab(
                            modifier = Modifier.fillMaxSize(),
                            selected = tab == selectedTab,
                            onClick = { selectedTab = tab },
                            text = { Text(text = tab.title) },
                            icon = { Icon(imageVector = tab.icon, contentDescription = null) }
                        )
                    }
                }

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                Tabs.Lists -> {
                    ShoppingListView(listOfShoppingList = shoppingLists)
                }

                Tabs.Profile -> {}
            }
        }
    }
}


@Preview(
    name = "Low DPI Small Screen",
    device = "spec:width=320dp,height=480dp,dpi=120"
)
@Composable
fun HomeScreenPreview() {
    val shoppingList = getMockShoppingList(1)[0]
    HomeScreen(viewModel = getMockViewModel(), navController = rememberNavController())
}


