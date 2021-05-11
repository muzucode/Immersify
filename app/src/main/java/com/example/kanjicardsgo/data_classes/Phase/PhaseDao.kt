package com.example.kanjicardsgo.data_classes.Phase
import androidx.room.*

@Dao
interface PhaseDao {

    @Insert
    suspend fun insertOne(phase: Phase)

    @Query("SELECT * FROM phase WHERE nativeTrackId in (:trackId)")
    suspend fun getAllPhasesInTrack(trackId: Int?): List<Phase>

}