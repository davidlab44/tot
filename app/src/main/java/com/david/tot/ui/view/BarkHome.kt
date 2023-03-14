package com.david.tot.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.david.tot.domain.model.DataProviderDumyProductList


    @Composable
    fun BarkHome() {
        val puppies = remember { DataProviderDumyProductList.fakeProductList }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = puppies,
                itemContent = {
                    PuppyListItem(puppy = it)
                })
        }
    }
