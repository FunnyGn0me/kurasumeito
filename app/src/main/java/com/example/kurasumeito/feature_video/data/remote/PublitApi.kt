package com.example.kurasumeito.feature_video.data.remote

import com.example.kurasumeito.feature_video.data.remote.responses.VideoTitles
import retrofit2.http.GET
import retrofit2.http.Query

interface PublitApi {
    @GET("list")
    suspend fun getVideoTitles(
        @Query("api_key") apiKey: String,
        @Query("api_timestamp") apiTimestamp: Int,
        @Query("api_nonce") apiNone: Int,
        @Query("api_signature") apiSignature: String
    ): VideoTitles
}