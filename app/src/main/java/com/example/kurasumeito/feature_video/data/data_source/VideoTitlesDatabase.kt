package com.example.kurasumeito.feature_video.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kurasumeito.feature_video.domain.model.VideoTitle


@Database(
    entities = [VideoTitle::class],
    version = 1
)
abstract class VideoTitlesDatabase: RoomDatabase() {

    abstract val videoTitlesDao: VideoTitlesDao

    companion object{
        const val DATABASE_NAME = "video_titles_db"
    }
}