package com.example.kurasumeito.feature_video.presentation.video

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


@Composable
fun VideoScreen(
    videoTitle: String,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        val url = "https://rasulyakupov.publit.io/file/${videoTitle.replace(" ","-")}.mp4"
        val exoPlayer = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.fromUri(Uri.parse(url))
        exoPlayer.setMediaItem(mediaItem)

        val playerView = PlayerView(context)
        playerView.player = exoPlayer

        DisposableEffect(AndroidView(factory = {playerView}, modifier = Modifier.fillMaxSize())){

            exoPlayer.prepare()
            exoPlayer.playWhenReady= true

            onDispose {
                exoPlayer.release()
            }
        }
    }
}