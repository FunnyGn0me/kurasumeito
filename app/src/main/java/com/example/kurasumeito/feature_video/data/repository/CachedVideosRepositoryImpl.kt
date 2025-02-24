package com.example.kurasumeito.feature_video.data.repository

import com.example.kurasumeito.feature_video.data.data_source.VideoTitlesDatabase
import com.example.kurasumeito.feature_video.domain.model.VideoTitle
import com.example.kurasumeito.feature_video.domain.repository.VideosRepository

class CachedVideosRepositoryImpl(private val videoTitlesDatabase: VideoTitlesDatabase) :
    VideosRepository {
    override suspend fun getTitles(): List<String> {
        return videoTitlesDatabase
            .videoTitlesDao
            .getVideoTitles()
            .map { videoTitle ->
                videoTitle
                    .title
            }
    }

    override suspend fun saveTitles(videoTitles: List<String>) {
        videoTitlesDatabase.videoTitlesDao.insertVideoTitles(videoTitles.map { VideoTitle(it) })

    }
}