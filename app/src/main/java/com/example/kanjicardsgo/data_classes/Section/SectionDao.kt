package com.example.kanjicardsgo.data_classes.Section
import androidx.room.*

@Dao
interface SectionDao {

    @Insert
    suspend fun insertOne(section: Section)

    @Query ("SELECT * FROM section WHERE nativePhaseName IN (:name)")
    suspend fun getAllByPhaseName(name: String?): List<Section>
}