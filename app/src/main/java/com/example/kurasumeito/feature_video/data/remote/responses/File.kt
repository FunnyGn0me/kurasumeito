package com.example.kurasumeito.feature_video.data.remote.responses

data class File(
    val created_at: String,
    val description: String,
    val extension: String,
    val folder: Any,
    val folder_id: Any,
    val height: Int,
    val hits: Int,
    val id: String,
    val option_ad: String,
    val option_download: String,
    val option_transform: String,
    val privacy: String,
    val public_id: Any,
    val size: Int,
    val tags: String,
    val title: String,
    val type: String,
    val updated_at: String,
    val url_download: String,
    val url_preview: String,
    val url_thumbnail: String,
    val versions: Int,
    val width: Int,
    val wm_id: Any
)