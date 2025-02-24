package com.example.kurasumeito.feature_video.presentation.videos

sealed class VideosEvent {
    object onRefresh: VideosEvent()
    class onSearchQueryChanged(val target: String): VideosEvent()
    object onSearch: VideosEvent()
    object onClearInternetExceptionState: VideosEvent()
}