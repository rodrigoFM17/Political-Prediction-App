package com.example.politicalprediction.home.data.model

data class Prediccion (
    val categoria: String,
    val probabilidad: Float
)
data class PredictResponse(
    val prediccion_principal: Prediccion
)
