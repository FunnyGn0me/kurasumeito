package com.example.kurasumeito.feature_video.presentation.videos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.Job

@Composable
@ExperimentalMaterial3Api
fun VideosScreen(
    innerPadding: PaddingValues,
    viewModel: VideosViewModel = hiltViewModel(),
    onNavigateToVideoScreen: (videoTitle: String) -> Unit,
    onInternetException: () -> Job
) {

    val state: VideosScreenState by remember { viewModel.state }

    val stateException = state.exceptionOccured

    LaunchedEffect(state) {
        if (stateException) {
            onInternetException()
            viewModel.onEvent(VideosEvent.onClearInternetExceptionState)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(10.dp)),
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.onEvent(VideosEvent.onSearch)
            }),
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(VideosEvent.onSearchQueryChanged(it)) },
            singleLine = true
        )
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                viewModel.onEvent(VideosEvent.onRefresh)
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(PaddingValues(10.dp))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(state.videoTitles.size) {
                    Video(
                        videoTitle = state.videoTitles[it],
                        onNavigateToVideoScreen = onNavigateToVideoScreen
                    )
                }
            }
        }
    }
}

@Composable
private fun Video(
    videoTitle: String,
    onNavigateToVideoScreen: (title: String) -> Unit
) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.clickable { onNavigateToVideoScreen(videoTitle) }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = "https://rasulyakupov.publit.io/file/t_2,w_320,h_240,c_fill/${
                    videoTitle.replace(
                        " ",
                        "-"
                    )
                }.png",
                contentDescription = videoTitle,
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = videoTitle
                )
            }
        }
    }
}