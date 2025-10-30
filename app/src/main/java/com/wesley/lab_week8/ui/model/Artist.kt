package com.wesley.lab_week8.ui.model

data class Artist(
    val idArtist: Int = 0,
    val nameArtist: String = "",
    val genre: String = "",
    val cover: String = "",
    val isError: Boolean = false,
    val errorMessage: String? = null
)
