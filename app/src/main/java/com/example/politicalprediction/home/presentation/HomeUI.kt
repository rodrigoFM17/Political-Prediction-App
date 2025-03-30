package com.example.politicalprediction.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.politicalprediction.home.data.model.PredictionDTO
import com.example.politicalprediction.home.data.model.StructuredData
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val content by homeViewModel.content.observeAsState("")
    val country by homeViewModel.country.observeAsState("")
    val politicTematic by homeViewModel.politicTematic.observeAsState("")
    val sources by homeViewModel.sources.observeAsState(0)
    val stadistics by homeViewModel.stadistics.observeAsState(0)
    val adjectives by homeViewModel.adjectives.observeAsState(0)
    val ideologicTerms by homeViewModel.ideologicTerms.observeAsState(0)
    val nWords by homeViewModel.nWords.observeAsState(0)
    val nImages by homeViewModel.nImages.observeAsState(0)
    val nQuotes by homeViewModel.nQuotes.observeAsState(0)
    val isRecognized by homeViewModel.isRecognized.observeAsState(false)
    val isSpecialized by homeViewModel.isSpecialized.observeAsState(false)
    val isFormal by homeViewModel.isFormal.observeAsState(false)
    val isEmocional by homeViewModel.isEmocional.observeAsState(false)

    val predicted by homeViewModel.predicted.observeAsState(false)
    val prediction by homeViewModel.prediction.observeAsState("")

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE9E9E9))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        TextField(
            value = content,
            placeholder = { Text(text = "Ingresa el contenido de la noticica") },
            onValueChange = homeViewModel::onChangeContent
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = country,
            placeholder = { Text(text = "Pais de origen") },
            onValueChange = homeViewModel::onChangeCountry
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = politicTematic,
            placeholder = { Text(text = "tematica de la noticia") },
            onValueChange = homeViewModel::onChangeTematic
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = sources.toString(),
            placeholder = { Text(text = "Numero de fuentes") },
            onValueChange = homeViewModel::onChangeSources
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = stadistics.toString(),
            placeholder = { Text(text = "Numero de estadisticas") },
            onValueChange = homeViewModel::onChangeStadistics
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = adjectives.toString(),
            placeholder = { Text(text = "Numero de adjetivos") },
            onValueChange = homeViewModel::onChangeAdjectives
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = ideologicTerms.toString(),
            placeholder = { Text(text = "Numero de terminos ideologicos") },
            onValueChange = homeViewModel::onChangeIdeologicTerms
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = nWords.toString(),
            placeholder = { Text(text = "Numero de palabras") },
            onValueChange = homeViewModel::onChangeNWords
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = nImages.toString(),
            placeholder = { Text(text = "Numero de imagenes") },
            onValueChange = homeViewModel::onChangeNImages
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = nQuotes.toString(),
            placeholder = { Text(text = "Numero de citas directas") },
            onValueChange = homeViewModel::onChangeNQuotes
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Es un medio reconocido?",
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isRecognized,
                onCheckedChange = homeViewModel::onChangeIsRecognized
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Es un medio especializado?",
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isSpecialized,
                onCheckedChange = homeViewModel::onChangeIsSpecialized
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Es Formal?",
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isFormal,
                onCheckedChange = homeViewModel::onChangeIsFormal
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Es Emocional?",
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                checked = isEmocional,
                onCheckedChange = homeViewModel::onChangeIsEmocional
            )
        }

        Button(
            onClick = {
                homeViewModel.viewModelScope.launch {
                    homeViewModel.predictNew(
                        PredictionDTO(
                            texto = content,
                            StructuredData(
                                numero = 1,
                                fuentes = sources,
                                estadisticas = stadistics,
                                numero_adjetivos = adjectives,
                                terminos_ideologicos = ideologicTerms,
                                numero_palabras = nWords,
                                imagenes = nImages,
                                citas_directas = nQuotes,
                                medio_reconocido = if (isRecognized) 1 else 0,
                                medio_especializado = if (isSpecialized) 1 else 0,
                                formalidad = if (isFormal) 1 else 0,
                                emocionalidad = if (isEmocional) 1 else 0,
                                pais_de_origen = country,
                                tema_principal = politicTematic
                            )
                        )
                    )
                }
            }
        ) {
            Text( text = "Predecir")
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (predicted) {
            Text( text = prediction)
        }

        Spacer(modifier = Modifier.height(60.dp))


    }
}