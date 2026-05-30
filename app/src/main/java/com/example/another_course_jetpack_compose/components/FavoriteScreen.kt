package com.example.another_course_jetpack_compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage

@Composable
fun FavoriteScreen() {
    val image1 = "https://plus.unsplash.com/premium_photo-1694819488591-a43907d1c5cc?q=80&w=714&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image2 = "https://images.unsplash.com/photo-1503256207526-0d5d80fa2f47?q=80&w=686&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image3 = "https://images.unsplash.com/photo-1601979031925-424e53b6caaa?q=80&w=687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image4 = "https://plus.unsplash.com/premium_photo-1666278379770-440439b08656?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image5 = "https://images.unsplash.com/photo-1588943211346-0908a1fb0b01?q=80&w=735&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image6 = "https://images.unsplash.com/photo-1537151608828-ea2b11777ee8?q=80&w=694&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    val image7 = "https://images.unsplash.com/photo-1534361960057-19889db9621e?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

    val dogList = remember {
        mutableStateListOf(
            Dog(
                name = "Toto",
                image = image1
            ),
            Dog(
                name = "Max",
                image = image2
            ),
            Dog(
                name = "Kira",
                image = image3
            ),
            Dog(
                name = "Daisky",
                image = image4
            ),
            Dog(
                name = "Chiquita",
                image = image5
            ),
            Dog(
                name = "Billy",
                image = image6
            ),
            Dog(
                name = "Mandy",
                image = image7
            ),
        )
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(dogList) { index, dog ->
            Card(
                modifier = Modifier.size(200.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier
                            .zIndex(1f)
                            .align(Alignment.TopEnd),
                        onClick = {
                            dogList.removeAt(index)
                            dogList.add(index, dog.copy(isFavorite = !dog.isFavorite))
                        }
                    ) {
                        Icon(imageVector = if (dog.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }

                    AsyncImage(
                        model = dog.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                    ) {
                        Text(
                            dog.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .zIndex(1f)
                                .padding(10.dp)
                                .align(Alignment.CenterStart)
                        )
                    }
                }
            }
        }
    }
}

data class Dog(
    val name: String,
    val image: String,
    val isFavorite: Boolean = false
)