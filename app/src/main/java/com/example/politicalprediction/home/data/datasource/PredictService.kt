package com.example.politicalprediction.home.data.datasource

import com.example.politicalprediction.home.data.model.PredictResponse
import com.example.politicalprediction.home.data.model.PredictionDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PredictService {

    @POST("predict")
    suspend fun predict(@Body request: PredictionDTO): Response<PredictResponse>
}