package com.example.kanjicardsgo.data_classes.Phase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Phase(
    @PrimaryKey(autoGenerate = true) val pid: Int?,
    val nativeTrackId: Int?,
    val name: String,
    // Describes what level in the track the phase is
    val level: Int?,
    // Describes how much of the phase is completed (percentage)
    val completion: Double
)
