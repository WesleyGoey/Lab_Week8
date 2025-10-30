package com.wesley.lab_week8.data.container

import com.google.gson.GsonBuilder
import com.wesley.lab_week8.data.repository.ArtistRepository
import com.wesley.lab_week8.data.service.ArtistService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistContainer {
    companion object {
        val BASE_URL = "https://www.theaudiodb.com/api/v1/json/123/"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val artistService: ArtistService by lazy {
        retrofit.create(ArtistService::class.java)
    }

    val artistRepository: ArtistRepository by lazy {
        ArtistRepository(artistService)
    }
}