package com.example.politicalprediction.home.data.model

data class StructuredData (
    val numero  : Int,
    val fuentes : Int,
    val estadisticas : Int,
    val numero_adjetivos : Int,
    val terminos_ideologicos : Int,
    val numero_palabras : Int,
    val imagenes : Int,
    val citas_directas : Int,
    val medio_reconocido : Int,
    val medio_especializado: Int,
    val formalidad : Int,
    val emocionalidad : Int ,
    val pais_de_origen : String,
    val tema_principal : String
)

data class PredictionDTO(
    val texto: String,
    val datos_estructurados: StructuredData
)
