package com.example.kanjicardsgo.data_classes.Track
import androidx.room.*

@Dao
interface TrackDao {

    @Insert
    suspend fun insertOne(track: Track)

    @Query("SELECT * FROM track")
    suspend fun getAll(): List<Track>

    @Query("SELECT * FROM track WHERE tid IN (:trackId)")
    suspend fun getById(trackId: Int?): Track

    @Query("SELECT * FROM track WHERE name IN (:trackName)")
    suspend fun getByName(trackName: String): Track

    @Query("SELECT * FROM track WHERE nativeUserId in (:userId)")
    suspend fun getAllByUserId(userId: Int?): List<Track>

    @Update
    suspend fun updateTrackName(track: Track)

}