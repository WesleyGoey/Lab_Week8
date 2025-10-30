package com.wesley.lab_week8.data.repository

import com.wesley.lab_week8.data.service.ArtistService
import com.wesley.lab_week8.ui.model.Album
import com.wesley.lab_week8.ui.model.Artist
import com.wesley.lab_week8.ui.model.Track
import java.io.IOException

class ArtistRepository(private val service: ArtistService) {
    suspend fun getArtistByName(artistName: String): Artist {
        val artist = service.getArtist(
            artistName = artistName
        ).body()!!

        return Artist(
            idArtist = artist.artists[0].idArtist.toInt(),
            nameArtist = artist.artists[0].strArtist,
            genre = artist.artists[0].strGenre,
            cover = artist.artists[0].strArtistThumb,
            isError = false,
            errorMessage = null
        )
    }

    suspend fun getAlbumArtistByName(artistName: String): Album {
        val albumArtist = service.getAlbumArtist(
            artistName = artistName
        ).body()!!

        return Album(
            idAlbum = albumArtist.album[0].idAlbum.toInt(),
            idArtist = albumArtist.album[0].idArtist.toInt(),
            nameAlbum = albumArtist.album[0].strAlbum,
            cover = albumArtist.album[0].strAlbumThumb,
            releaseDate = albumArtist.album[0].intYearReleased,
            genre = albumArtist.album[0].strGenre,
            description = albumArtist.album[0].strDescriptionEN,
            isError = false,
            errorMessage = null
        )
    }

    suspend fun getTrackAlbumById(albumId: Int): Track {
        val trackAlbum = service.getTrackAlbum(
            albumId = albumId
        ).body()!!

        return Track(
            idTrack = trackAlbum.track[0].idTrack.toInt(),
            idAlbum = trackAlbum.track[0].idAlbum.toInt(),
            nameTrack = trackAlbum.track[0].strTrack,
            duration = trackAlbum.track[0].intDuration.toInt(),
            isError = false,
            errorMessage = null
        )
    }
}