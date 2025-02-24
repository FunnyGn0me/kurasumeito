package com.example.kurasumeito.feature_video.domain.use_case

import com.example.kurasumeito.feature_video.domain.repository.VideosRepository

class SaveVideoTitles (val videosRepository: VideosRepository) {
    suspend operator fun invoke(videoTitles: List<String>){
        videosRepository.saveTitles(videoTitles)
    }
}