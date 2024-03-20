package com.futurecoder.eshopp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class Address(
    @ColumnInfo(name = "address")
    var address: String = "",
    @ColumnInfo(name = "city")
    var city: String = "",
    @ColumnInfo(name = "state")
    var state: String = "",
    @ColumnInfo(name = "country")
    var country: String = "",
    @ColumnInfo(name = "postalCode")
    var postalCode: String = ""
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 1
}
