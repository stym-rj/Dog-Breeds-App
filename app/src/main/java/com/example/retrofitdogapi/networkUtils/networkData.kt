package com.example.retrofitdogapi.networkUtils

import com.example.retrofitdogapi.imageUrlPrefix
import com.google.gson.annotations.SerializedName

data class BreedDetails (
    @SerializedName("name")
    val name: String,
    // this @SerializedName is the name of the key corresponding to the json.

    @SerializedName("breed_group")
    val category: String,
    //here, there is no key as "category", but we can use @SerializedName("breed_group) to rename that key as "category"

    @SerializedName("origin")
    val origin: String,

    @SerializedName("reference_image_id")
    val image: String
)
