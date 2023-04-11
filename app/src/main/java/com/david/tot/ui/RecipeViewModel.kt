package com.david.tot.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.tot.domain.GetRecipesUseCase
import com.david.tot.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    var recipeModel by mutableStateOf<List<Recipe>>(emptyList())
    val st="%Rollitos%"
    fun onCreate() {
        //viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getRecipesUseCase.invoke(st)
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }
}