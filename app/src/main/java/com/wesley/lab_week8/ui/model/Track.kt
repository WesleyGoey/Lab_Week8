package com.wesley.lab_week8.ui.model

data class Track(
    val idTrack: Int = 0,
    val idAlbum: Int = 0,
    val nameTrack: String = "",
    val duration: Int = 0,
    val isError: Boolean = false,
    val errorMessage: String? = null
)
