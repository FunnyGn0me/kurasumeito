package com.example.kurasumeito.feature_video.domain.repository

interface VideosRepository {
    suspend fun getTitles(): List<String>
    suspend fun saveTitles(videoTitles: List<String>)
}
