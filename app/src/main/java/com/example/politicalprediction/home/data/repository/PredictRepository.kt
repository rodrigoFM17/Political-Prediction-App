package com.example.politicalprediction.home.data.repository

import com.example.politicalprediction.core.network.RetrofitHelper
import com.example.politicalprediction.home.data.model.PredictResponse
import com.example.politicalprediction.home.data.model.PredictionDTO

class PredictRepository {

    private val predictService = RetrofitHelper.predictService

    suspend fun predict (predictionDTO: PredictionDTO): Result<PredictResponse> {
        return try {
            val response = predictService.predict(predictionDTO)

            if(response.isSuccessful){
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}