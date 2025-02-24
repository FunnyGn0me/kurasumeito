package com.example.kurasumeito.feature_video.presentation.videos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurasumeito.feature_video.domain.use_case.VideosUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val videosUseCases: VideosUseCases
) : ViewModel() {

    private val _state = mutableStateOf(
        VideosScreenState(
            isRefreshing = false,
            videoTitles = emptyList(),
            exceptionOccured = false,
            searchQuery = ""
        )
    )
    val state: State<VideosScreenState> = _state

    init {
        loadFromCache()
    }

    private fun loadFromCache() {
        viewModelScope.launch {

            val videoTitlesFromCache: List<String> =
                async(Dispatchers.IO) { videosUseCases.getVideoTitlesFromCache() }.await()
            if (videoTitlesFromCache.isEmpty()) {
                val videoTitles: List<String>
                try {
                    videoTitles = videosUseCases.getVideoTitles()
                    _state.value =
                        state.value.copy(videoTitles = videoTitles, exceptionOccured = false)
                    async(Dispatchers.IO) { videosUseCases.saveVideoTitlesInCache(videoTitles) }.await()
                } catch (exception: Throwable) {
                    _state.value = state.value.copy(exceptionOccured = true)
                }
            } else {
                _state.value = state.value.copy(videoTitles = videoTitlesFromCache)
            }
        }
    }

    fun onEvent(event: VideosEvent) {
        when (event) {
            is VideosEvent.onRefresh -> {
                _state.value = state.value.copy(isRefreshing = true)
                viewModelScope.launch {
                    val refreshedVideoTitles: List<String>
                    try {
                        refreshedVideoTitles = videosUseCases.getVideoTitles()
                        _state.value = state.value.copy(
                            isRefreshing = false,
                            videoTitles = refreshedVideoTitles.filter { it.startsWith(state.value.searchQuery) },
                            exceptionOccured = false
                        )
                        async(Dispatchers.IO) {
                            videosUseCases.saveVideoTitlesInCache(
                                refreshedVideoTitles
                            )
                        }.await()
                    } catch (exception: Throwable) {
                        _state.value =
                            state.value.copy(isRefreshing = false, exceptionOccured = true)
                    }
                }
            }

            is VideosEvent.onSearchQueryChanged -> {
                _state.value = state.value.copy(
                    searchQuery = event.target
                )
            }

            VideosEvent.onSearch -> {
                if (state.value.searchQuery.isEmpty()) {
                    loadFromCache()
                } else {
                    onEvent(VideosEvent.onRefresh)
                }
                _state.value =
                    state.value.copy(videoTitles = state.value.videoTitles.filter {
                        it.startsWith(
                            state.value.searchQuery
                        )
                    })
            }

            VideosEvent.onClearInternetExceptionState -> {
                _state.value = state.value.copy(exceptionOccured = false)
            }
        }
    }
}