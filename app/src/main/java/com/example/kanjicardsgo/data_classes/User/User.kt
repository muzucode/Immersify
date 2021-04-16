package com.example.kanjicardsgo.data_classes.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    val username: String,
    val password: String,
    val email: String,

)