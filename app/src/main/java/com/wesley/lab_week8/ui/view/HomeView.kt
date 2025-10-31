package com.wesley.lab_week8.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.wesley.lab_week8.ui.route.AppView
import com.wesley.lab_week8.ui.viewmodel.ArtistViewModel

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    val artist by viewModel.artist.collectAsState()
    val listAlbum by viewModel.listAlbum.collectAsState()

    // Calculate height for LazyVerticalGrid based on number of items
    val totalHeight = ((listAlbum.size + 1) / 2) * 240.dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF282828))
    ) {
        item {
            Row(
                modifier = modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(artist.cover),
                        contentDescription = "Cover",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF303030),
                                RoundedCornerShape(12.dp)
                            )
                            .size(340.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp)
                    ) {
                        Text(
                            artist.nameArtist,
                            color = Color(0xFFA6A07A),
                            fontSize = 21.sp,
                        )
                        Text(
                            artist.genre,
                            color = Color(0xFFA6A07A),
                            fontSize = 15.sp,
                        )
                    }
                }
            }

            Column(
                modifier = modifier
                    .padding(start = 16.dp, end = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Album",
                    color = Color(0xFFA6A07A)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false,
                    modifier = modifier.height(totalHeight)
                ) {
                    items(listAlbum) { album ->
                        AlbumCard(
                            name = album.nameAlbum,
                            releasedDate = album.releaseDate,
                            genre = album.genre,
                            cover = album.cover,
                            modifier = modifier,
                            onClick = {
                                navController.navigate("${AppView.AlbumDetailView.name}/${album.idAlbum}")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeView()
}
