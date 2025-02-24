package com.example.kurasumeito.feature_video.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kurasumeito.feature_video.presentation.video.VideoScreen
import com.example.kurasumeito.feature_video.presentation.videos.VideosScreen
import com.example.kurasumeito.ui.theme.KurasumeitoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KurasumeitoTheme {
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = VideosScreenRoute
                    ) {
                        composable<VideosScreenRoute> {
                            VideosScreen(
                                innerPadding = innerPadding,
                                onNavigateToVideoScreen = { title: String ->
                                    navController.navigate(
                                        VideoScreenRoute(
                                            title
                                        )
                                    )
                                },
                                onInternetException = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Произошла проблема при загрузке данных")
                                    }
                                }
                            )
                        }
                        composable<VideoScreenRoute> {
                            val route = it.toRoute<VideoScreenRoute>()
                            VideoScreen(
                                videoTitle = route.title,
                                innerPadding = innerPadding
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object VideosScreenRoute

@Serializable
data class VideoScreenRoute(
    val title: String
)