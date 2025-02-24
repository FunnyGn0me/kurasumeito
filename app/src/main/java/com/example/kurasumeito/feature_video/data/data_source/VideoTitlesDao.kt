package com.example.kurasumeito.feature_video.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kurasumeito.feature_video.domain.model.VideoTitle


@Dao
interface VideoTitlesDao {

    @Query("SELECT * FROM videoTitle")
    fun getVideoTitles(): List<VideoTitle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoTitles(videoTitles: List<VideoTitle>)
}