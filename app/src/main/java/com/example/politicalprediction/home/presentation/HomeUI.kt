package com.example.politicalprediction.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val content by homeViewModel.content.observeAsState()
    val country by homeViewModel.country.observeAsState()
    val politicTematic by homeViewModel.politicTematic.observeAsState()
    val sources by homeViewModel.sources.observeAsState()
    val stadistics by homeViewModel.stadistics.observeAsState()
    val adjectives by homeViewModel.adjectives.observeAsState()
    val ideologicTerms by homeViewModel.ideologicTerms.observeAsState()
    val nWords by homeViewModel.nWords.observeAsState()
    val nImages by homeViewModel.nImages.observeAsState()
    val nQuotes by homeViewModel.nQuotes.observeAsState()
    val isRecognized by homeViewModel.isRecognized.observeAsState()
    val isSpecialized by homeViewModel.isSpecialized.observeAsState()
    val isFormal by homeViewModel.isFormal.observeAsState()
    val isEmocional by homeViewModel.isEmocional.observeAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE9E9E9))
    ) {

        TextField(
            value = content,
            placeholder = "Ingresa el contenido de la noticia",
            onValueChange = homeViewModel::onChangeContent
        )

    }
}