package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "price") var price: Int = 0,
    @ColumnInfo(name = "requested_amount") val requested_amount: Int = 0,
    @ColumnInfo(name = "is_milligram") val is_milligram: Int = 0,
    @ColumnInfo(name = "is_unit") val is_unit: Int = 0
    )
fun Product.toDomain() = Product(id,name,image,description,price,requested_amount,is_milligram,is_unit)
fun Product.toDatabase() = Product(name = name, image =  image, description = description, price = price, requested_amount = requested_amount, is_milligram = is_milligram, is_unit = is_unit)