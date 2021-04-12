package com.example.kanjicardsgo.data_classes.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo val username: String?,
    @ColumnInfo val password: String?
)
