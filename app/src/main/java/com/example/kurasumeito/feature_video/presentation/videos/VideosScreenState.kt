package com.example.kurasumeito.feature_video.presentation.videos

data class VideosScreenState(
    val isRefreshing: Boolean,
    val videoTitles: List<String>,
    val exceptionOccured: Boolean,
    val searchQuery: String
)