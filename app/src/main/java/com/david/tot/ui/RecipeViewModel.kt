package com.david.tot.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.tot.domain.GetRecipesUseCase
import com.david.tot.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    var recipeModel by mutableStateOf<List<Recipe>>(emptyList())

    fun onCreate() {
        viewModelScope.launch {
            val result = getRecipesUseCase()
            if (!result.isNullOrEmpty()) {
                recipeModel =result
            }
        }
    }
}