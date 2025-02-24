package com.example.kurasumeito.feature_video.data.remote.responses

data class VideoTitles(
    val code: Int,
    val files: List<File>,
    val files_count: Int,
    val files_total: Int,
    val limit: Int,
    val offset: Int,
    val success: Boolean
)