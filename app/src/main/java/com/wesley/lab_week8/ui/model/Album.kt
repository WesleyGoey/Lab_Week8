package com.wesley.lab_week8.ui.model

data class Album(
    val idAlbum: Int = 0,
    val idArtist: Int = 0,
    val nameAlbum: String = "",
    val cover: String = "",
    val releasedDate: String = "",
    val genre: String = "",
    val description: String = "",
    val isError: Boolean = false,
    val errorMessage: String? = null
)