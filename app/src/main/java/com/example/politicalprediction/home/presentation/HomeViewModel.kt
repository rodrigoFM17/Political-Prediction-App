package com.example.politicalprediction.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.politicalprediction.home.data.model.PredictionDTO
import com.example.politicalprediction.home.data.repository.PredictRepository

class HomeViewModel : ViewModel() {

    private val _content = MutableLiveData<String>()
    private val _country = MutableLiveData<String>()
    private val _politicTematic = MutableLiveData<String>()
    private val _sources = MutableLiveData<Int>()
    private val _stadistics = MutableLiveData<Int>()
    private val _adjectives = MutableLiveData<Int>()
    private val _ideologicTerms = MutableLiveData<Int>()
    private val _nWords = MutableLiveData<Int>()
    private val _nImages = MutableLiveData<Int>()
    private val _nQuotes = MutableLiveData<Int>()
    private val _isRecognized = MutableLiveData<Boolean>()
    private val _isSpecialized = MutableLiveData<Boolean>()
    private val _isFormal = MutableLiveData<Boolean>()
    private val _isEmocional = MutableLiveData<Boolean>()

    private val _predicted = MutableLiveData<Boolean>()
    private val _prediction = MutableLiveData<String>()

    val content: LiveData<String> = _content
    val country: LiveData<String> = _country
    val politicTematic: LiveData<String> = _politicTematic
    val sources: LiveData<Int> = _sources
    val stadistics: LiveData<Int> = _stadistics
    val adjectives: LiveData<Int> = _adjectives
    val ideologicTerms: LiveData<Int> = _ideologicTerms
    val nWords: LiveData<Int> = _nWords
    val nImages: LiveData<Int> = _nImages
    val nQuotes: LiveData<Int> = _nQuotes
    val isRecognized: LiveData<Boolean> = _isRecognized
    val isSpecialized: LiveData<Boolean> = _isSpecialized
    val isFormal: LiveData<Boolean> = _isFormal
    val isEmocional: LiveData<Boolean> = _isEmocional

    val predicted: LiveData<Boolean> = _predicted
    val prediction: LiveData<String> = _prediction

    fun onChangeContent(value: String) {
        _content.value = value
    }

    fun onChangeCountry(value: String) {
        _country.value = value
    }

    fun onChangeTematic(value: String) {
        _politicTematic.value = value
    }

    fun onChangeSources(value: String) {
        _sources.value = value.toInt()
    }

    fun onChangeStadistics(value: String) {
        _stadistics.value = value.toInt()
    }

    fun onChangeAdjectives(value: String) {
        _adjectives.value = value.toInt()
    }
    fun onChangeIdeologicTerms(value: String) {
        _ideologicTerms.value = value.toInt()
    }

    fun onChangeNWords(value: String) {
        _nWords.value = value.toInt()
    }

    fun onChangeNImages(value: String) {
        _nImages.value = value.toInt()
    }

    fun onChangeNQuotes(value: String) {
        _nQuotes.value = value.toInt()
    }

    fun onChangeIsRecognized(value: Boolean) {
        _isRecognized.value = value
    }

    fun onChangeIsSpecialized(value: Boolean) {
        _isSpecialized.value = value
    }

    fun onChangeIsFormal(value: Boolean) {
        _isFormal.value = value
    }

    fun onChangeIsEmocional(value: Boolean) {
        _isEmocional.value = value
    }

    suspend fun predictNew(predictionDTO: PredictionDTO) {
        _predicted.value = false
        val repository = PredictRepository()

        val result = repository.predict(predictionDTO)

        result.onSuccess {
            data ->

            Log.d("API", data.prediccion_principal.categoria)
            _prediction.value = data.prediccion_principal.categoria
            _predicted.value = true
        }

    }
}