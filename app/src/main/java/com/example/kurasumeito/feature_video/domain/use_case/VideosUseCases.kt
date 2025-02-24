package com.example.kurasumeito.feature_video.domain.use_case

data class VideosUseCases(
    val getVideoTitles: GetVideoTitles,
    val getVideoTitlesFromCache: GetVideoTitles,
    val saveVideoTitlesInCache: SaveVideoTitles
)