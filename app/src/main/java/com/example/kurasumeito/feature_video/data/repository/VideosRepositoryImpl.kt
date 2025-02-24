package com.example.kurasumeito.feature_video.data.repository

import com.example.kurasumeito.feature_video.data.remote.PublitApi
import com.example.kurasumeito.feature_video.domain.repository.VideosRepository
import com.example.kurasumeito.feature_video.domain.util.toSha1

class VideosRepositoryImpl(private val api: PublitApi, private val key: String, private val secret: String):VideosRepository {
    override suspend fun getTitles(): List<String> {
        val nonce = (10_000_000.. 99_999_999).random()
        val stamp = (System.currentTimeMillis()/1000).toInt()

        return api
            .getVideoTitles(key, stamp ,nonce, (stamp.toString() + nonce.toString() + secret).toSha1())
            .files
            .map{it.title}
    }

    override suspend fun saveTitles(videoTitles: List<String>) {
    }
}