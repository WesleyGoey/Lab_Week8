package com.wesley.lab_week8.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wesley.lab_week8.ui.viewmodel.ArtistViewModel

@Composable
fun AlbumDetailView(
    modifier: Modifier = Modifier,
    viewModel: ArtistViewModel = viewModel(),
    albumId: Int
) {

}

@Preview
@Composable
private fun AlbumDetailPreview() {
    AlbumDetailView(
        albumId = 0
    )
}