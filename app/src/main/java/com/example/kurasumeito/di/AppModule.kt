package com.example.kurasumeito.di

import android.app.Application
import androidx.compose.runtime.key
import androidx.room.Room
import com.example.kurasumeito.BuildConfig
import com.example.kurasumeito.feature_video.data.remote.PublitApi
import com.example.kurasumeito.feature_video.data.data_source.VideoTitlesDatabase
import com.example.kurasumeito.feature_video.data.repository.VideosRepositoryImpl
import com.example.kurasumeito.feature_video.data.repository.CachedVideosRepositoryImpl
import com.example.kurasumeito.feature_video.domain.repository.VideosRepository
import com.example.kurasumeito.feature_video.domain.use_case.GetVideoTitles
import com.example.kurasumeito.feature_video.domain.use_case.SaveVideoTitles
import com.example.kurasumeito.feature_video.domain.use_case.VideosUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDataBase(app: Application): VideoTitlesDatabase {
        return Room.databaseBuilder(
            app,
            VideoTitlesDatabase::class.java,
            VideoTitlesDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    @Named("remote")
    fun provideVideosRepository(
        api: PublitApi,
        @Named("key") key: String,
        @Named("secret") secret: String
    ): VideosRepository {
        return VideosRepositoryImpl(api, key,  secret)
    }

    @Provides
    @Singleton
    @Named("key")
    fun provideKey() = BuildConfig.key



    @Provides
    @Singleton
    @Named("secret")
    fun provideSecret() = BuildConfig.secret


    @Provides
    @Singleton
    fun provideVideosUseCases(
        @Named("remote") videosRepositoryRemote: VideosRepository,
        @Named("local") videosRepositoryLocal: VideosRepository
    ): VideosUseCases {
        return VideosUseCases(
            getVideoTitles = GetVideoTitles(videosRepositoryRemote),
            getVideoTitlesFromCache = GetVideoTitles(videosRepositoryLocal),
            saveVideoTitlesInCache = SaveVideoTitles(videosRepositoryLocal)
        )
    }

    @Provides
    @Singleton
    fun provideApi(): PublitApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.publit.io/v1/files/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PublitApi::class.java)
    }

    @Provides
    @Singleton
    @Named("local")
    fun provideVideosRepositoryCache(videoTitlesDatabase: VideoTitlesDatabase): VideosRepository {
        return CachedVideosRepositoryImpl(videoTitlesDatabase)
    }

}