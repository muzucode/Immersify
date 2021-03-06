package com.example.kanjicardsgo.data_classes.Track

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.kanjicardsgo.data_classes.Phase.Phase
import com.example.kanjicardsgo.data_classes.Section.Section
import com.example.kanjicardsgo.data_classes.User.User

@Entity
data class Track(
        @PrimaryKey(autoGenerate = true) val tid: Int?,
        val nativeUserId: Int?,
        val name: String,
        val language: String,
        val mode: String,
        val currentPhase: Int
        )


@Entity
data class Question(
        @PrimaryKey(autoGenerate = true) val qid: Int?,
        val nativeSectionId: Int?,
        val format: String,
        val title: String,
        val content: String?
        )

// Relationships
data class UserWithTracks(
        @Embedded val user: User,
        @Relation(
                parentColumn = "uid",
                entityColumn = "nativeUserId"
        )
        val tracks: List<Track>
)

data class TrackWithPhases(
        @Embedded val track: Track,
        @Relation(
                parentColumn = "tid",
                entityColumn = "nativeTrackId"
        )
        val phases: List<Phase>
)

data class PhaseWithSections(
        @Embedded val phase: Phase,
        @Relation(
                parentColumn = "pid",
                entityColumn = "nativePhaseId"
        )
        val sections: List<Section>
)

data class SectionWithQuestions(
        @Embedded val section: Section,
        @Relation(
                parentColumn = "sid",
                entityColumn = "nativeSectionId"
        )
        val phases: List<Question>
)
