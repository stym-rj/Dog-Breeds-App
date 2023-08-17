package com.example.retrofitdogapi.networkUtils

import com.example.retrofitdogapi.BASE_URL
import com.example.retrofitdogapi.BREEDS_ENDPOINT
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DogDataService {
    @GET(BREEDS_ENDPOINT)
    fun fetchDogBreeds(): Call<List<BreedDetails>>
}

val api: DogDataService by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogDataService::class.java)
}