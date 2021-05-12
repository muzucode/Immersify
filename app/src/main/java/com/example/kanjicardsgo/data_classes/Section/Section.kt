package com.example.kanjicardsgo.data_classes.Section

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Section(
    @PrimaryKey(autoGenerate = true) val sid: Int?,
    val nativePhaseName: String,
    val name: String,
    val completion: Double
)
