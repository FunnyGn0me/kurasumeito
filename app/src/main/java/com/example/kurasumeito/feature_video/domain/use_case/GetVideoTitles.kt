package com.example.kurasumeito.feature_video.domain.use_case

import com.example.kurasumeito.feature_video.domain.repository.VideosRepository

class GetVideoTitles (val videosRepository: VideosRepository) {
    suspend operator fun invoke( ): List<String>{
        return videosRepository.getTitles()
    }
}