package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int = 0,
    @ColumnInfo(name = "requested_amount") val requested_amount: Int = 0,
    @ColumnInfo(name = "is_milligram") val is_milligram: Int = 0,
    @ColumnInfo(name = "is_unit") val is_unit: Int = 0
    )
fun Product.toDomain() = Product(local_id,id,name,image,description,price,requested_amount,is_milligram,is_unit)
fun Product.toDatabase() = Product(local_id=local_id, id=id, name=name, image=image, description = description, price = price, requested_amount = requested_amount, is_milligram = is_milligram, is_unit = is_unit)