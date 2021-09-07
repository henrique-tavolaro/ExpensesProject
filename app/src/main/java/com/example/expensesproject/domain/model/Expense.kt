package com.example.expensesproject.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expense")
data class Expense(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "category")
    val category: Category,
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "date")
    val date: Date


){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Expense

        if (id != other.id) return false
        if (name != other.name) return false
        if (category != other.category) return false
        if (value != other.value) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}

