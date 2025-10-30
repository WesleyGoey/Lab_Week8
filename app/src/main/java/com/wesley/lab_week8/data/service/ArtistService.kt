package com.wesley.lab_week8.data.service

import com.wesley.lab_week8.data.dto.ResponseAlbumArtist
import com.wesley.lab_week8.data.dto.ResponseArtist
import com.wesley.lab_week8.data.dto.ResponseDetailAlbum
import com.wesley.lab_week8.data.dto.ResponseTrackAlbum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {
    @GET("search.php")
    suspend fun getArtist(
        @Query("s") artistName: String
    ): Response<ResponseArtist>

    @GET("searchalbum.php")
    suspend fun getAlbumArtist(
        @Query("s") artistName: String
    ): Response<ResponseAlbumArtist>

    @GET("album.php")
    suspend fun getDetailAlbum(
        @Query("s") albumId: Int
    ): Response<ResponseDetailAlbum>

    @GET("track.php")
    suspend fun getTrackAlbum(
        @Query("s") albumId: Int
    ): Response<ResponseTrackAlbum>
}