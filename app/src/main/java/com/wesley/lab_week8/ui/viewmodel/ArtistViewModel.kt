package com.wesley.lab_week8.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesley.lab_week8.data.container.ArtistContainer
import com.wesley.lab_week8.ui.model.Album
import com.wesley.lab_week8.ui.model.Artist
import com.wesley.lab_week8.ui.model.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {
    private val _artist = MutableStateFlow(Artist())
    val artist: StateFlow<Artist> = _artist.asStateFlow()

    private val _listAlbum = MutableStateFlow<List<Album>>(emptyList())
    val listAlbum: StateFlow<List<Album>> = _listAlbum.asStateFlow()
    private val _detailAlbum = MutableStateFlow(Album())
    val detailAlbum: StateFlow<Album> = _detailAlbum.asStateFlow()

    private val _listTrack = MutableStateFlow<List<Track>>(emptyList())
    val listTrack: StateFlow<List<Track>> = _listTrack.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadArtist("Justin Bieber")
    }

    fun loadArtist(artistName: String) {
        if (artistName.isBlank()) {
            _artist.value = _artist.value.copy(
                isError = true, errorMessage = "Error. Tidak ada koneksi internet."
            )
            _listAlbum.value = emptyList()
            _listTrack.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                val artistData = ArtistContainer().artistRepository.getArtistByName(artistName)
                _artist.value = artistData.copy(
                    isError = false, errorMessage = null
                )
                loadAlbum(artistName)
            } catch (e: Exception) {
                _artist.value = _artist.value.copy(
                    isError = true, errorMessage = "Error. Tidak ada koneksi internet."
                )
            }
        }
    }

    fun loadAlbum(artistName: String) {
        viewModelScope.launch {
            try {
                val albumData = ArtistContainer().artistRepository.getAlbumArtistByName(artistName)
                _listAlbum.value = albumData
            } catch (e: Exception) {
                _listAlbum.value = emptyList()
            }
        }
    }

    fun loadDetailAlbum(albumId: Int) {
        viewModelScope.launch {
            try {
                val detailAlbumData = ArtistContainer().artistRepository.getDetailAlbumById(albumId)
                _detailAlbum.value = detailAlbumData
            } catch (e: Exception) {
                _detailAlbum.value = Album(
                    isError = true,
                    errorMessage = "Error. Tidak ada koneksi internet."
                )
            }
        }
    }

    fun loadTrack(albumId: Int) {
        viewModelScope.launch {
            try {
                val trackData = ArtistContainer().artistRepository.getTrackAlbumById(albumId)
                _listTrack.value = trackData
            } catch (e: Exception) {
                _listTrack.value = emptyList()
            }
        }
    }

    fun formatDuration(durationMs: Int): String {
        val totalSeconds = durationMs / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "%d:%02d".format(minutes, seconds)
    }
}