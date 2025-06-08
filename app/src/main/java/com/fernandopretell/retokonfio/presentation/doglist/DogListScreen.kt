package com.fernandopretell.retokonfio.presentation.doglist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.fernandopretell.retokonfio.domain.model.Dog


@Composable
fun DogListScreen(viewModel: DogListViewModel = hiltViewModel()) {
    val dogs by viewModel.dogs
    val loading by viewModel.isLoading
    val error by viewModel.errorMessage

    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (error != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(error ?: "", color = MaterialTheme.colorScheme.onBackground)
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(
                items = dogs,
                key = { it.id }
            ) { dog ->
                DogItem(dog)
            }
        }

    }
}


@Composable
fun DogItem(dog: Dog) {
    Card(Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(Modifier.padding(16.dp)) {
            AsyncImage(
                model = dog.imageUrl,
                contentDescription = dog.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    dog.name,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "Breed: ${dog.breed}",
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    "Age: ${dog.age}",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
