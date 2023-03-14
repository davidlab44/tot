package com.david.tot.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.david.tot.domain.model.Product

@Composable
fun PuppyListItem(puppy: Product) {
    Row {
        Column {
            Text(text = puppy.name, style = typography.h6)
            Text(text = "VIEW DETAIL", style = typography.caption)
        }
    }
}