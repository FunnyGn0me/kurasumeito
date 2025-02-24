package com.example.kurasumeito.feature_video.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoTitle(
    @PrimaryKey val title: String
)
