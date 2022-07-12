package com.example.sharerecipy

import com.google.gson.annotations.SerializedName
import java.net.URL

data class RecipeList(
    @SerializedName("COOKRCP01") val list : RecipeDto
)

data class RecipeDto(
    @SerializedName("row") val recipes : List<Recipe>
)

data class Recipe(
    @SerializedName("RCP_NM") val name : String,
    @SerializedName("ATT_FILE_NO_MAIN") val imageUrl: URL,
    @SerializedName("RCP_PARTS_DTLS") val ingredient: String
)
