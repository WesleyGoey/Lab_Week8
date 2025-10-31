package com.wesley.lab_week8.data.repository

import com.wesley.lab_week8.data.service.ArtistService
import com.wesley.lab_week8.ui.model.Album
import com.wesley.lab_week8.ui.model.Artist
import com.wesley.lab_week8.ui.model.Track
import java.io.IOException
import kotlin.collections.emptyList

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


    suspend fun getAlbumArtistByName(artistName: String): List<Album> {
        val albumArtist = service.getAlbumArtist(
            artistName = artistName
        ).body()!!
        return albumArtist.album.map { albumData ->
            Album(
                idAlbum = albumData.idAlbum.toInt(),
                idArtist = albumData.idArtist.toInt(),
                nameAlbum = albumData.strAlbum,
                cover = albumData.strAlbumThumb ?: "",
                releaseDate = albumData.intYearReleased ?: "",
                genre = albumData.strGenre ?: "",
                description = albumData.strDescriptionEN ?: "",
                isError = false,
                errorMessage = null
            )
        }
    }

    suspend fun getDetailAlbumById(albumId: Int): Album {
        val detailAlbum = service.getDetailAlbum(
            albumId = albumId
        ).body()!!

        return Album(
            idAlbum = detailAlbum.album[0].idAlbum.toInt(),
            idArtist = detailAlbum.album[0].idArtist.toInt(),
            nameAlbum = detailAlbum.album[0].strAlbum,
            cover = detailAlbum.album[0].strAlbumThumb,
            releaseDate = detailAlbum.album[0].intYearReleased,
            genre = detailAlbum.album[0].strGenre,
            description = detailAlbum.album[0].strDescriptionEN,
            isError = false,
            errorMessage = null
        )
    }

    suspend fun getTrackAlbumById(albumId: Int): List<Track> {
        val trackAlbum = service.getTrackAlbum(
            albumId = albumId
        ).body()!!

        return trackAlbum.track.map { trackData ->
            Track(
                idTrack = trackData.idTrack.toInt(),
                idAlbum = trackData.idAlbum.toInt(),
                nameTrack = trackData.strTrack,
                duration = trackData.intDuration.toInt(),
                isError = false,
                errorMessage = null
            )
        }
    }
}