package com.wilmer.fooddataandlist.viewmodel

import androidx.lifecycle.ViewModel
import com.wilmer.fooddataandlist.data.remote.getToken
import com.wilmer.fooddataandlist.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor( private val repository: Repository) : ViewModel() {

    fun getFoods() {




    }

    suspend fun getAccessToken() {
        getToken()
    }


}
