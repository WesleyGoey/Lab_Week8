package com.wesley.lab_week8.ui.view

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.wesley.lab_week8.data.dto.Album
import com.wesley.lab_week8.ui.viewmodel.ArtistViewModel
import kotlin.Int

@Composable
fun AlbumDetailView(
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = viewModel(),
    albumId: Int
) {
    LaunchedEffect(albumId) {
        viewModel.loadDetailAlbum(albumId)
        viewModel.loadTrack(albumId)
    }

    val artist by viewModel.artist.collectAsState()
    val detailAlbum by viewModel.detailAlbum.collectAsState()
    val listTrack by viewModel.listTrack.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF282828))
    ) {
        item {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color(0xFF1c2021))
                    .border(
                        width = 2.dp,
                        color = Color(0xFF404241),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp)
            ) {
                AsyncImage(
                    model = detailAlbum.cover,
                    contentDescription = "Artist Cover",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp)),
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = detailAlbum.nameAlbum,
                    color = Color(0xFFaeaa9e),
                    fontSize = 22.sp,
                )
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = detailAlbum.releasedDate,
                        fontSize = 14.sp,
                        color = Color(0xFFaeaa9e),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .size(3.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFaeaa9e))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = detailAlbum.genre,
                        fontSize = 14.sp,
                        color = Color(0xFFaeaa9e),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = detailAlbum.description,
                    color = Color(0xFFaeaa9e),
                    fontSize = 14.sp,
                )


            }

        }
    }
}

@Preview
@Composable
private fun AlbumDetailPreview() {
    AlbumDetailView(
        albumId = 2113853
    )
}