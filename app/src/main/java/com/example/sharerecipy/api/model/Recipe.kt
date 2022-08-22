package com.example.sharerecipy.api.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class RecipeList(
    @SerializedName("COOKRCP01")
    val list : RecipeDto
)

data class RecipeDto(
    @SerializedName("row") val recipes : List<Recipe>
)

data class Recipe(
    // 일련번호
    @SerializedName("RCP_SEQ") val id : Int,
    @SerializedName("RCP_NM") val name : String,
    @SerializedName("ATT_FILE_NO_MAIN") val imageUrl: URL,
    @SerializedName("RCP_PARTS_DTLS") val ingredient: String,
    @SerializedName("RCP_PAT2") val type: String,
    @SerializedName("MANUAL01") val manual1: String?,
    @SerializedName("MANUAL_IMG01") val manual1_img: String?,
    @SerializedName("MANUAL02") val manual2: String?,
    @SerializedName("MANUAL_IMG02") val manual2_img: String?,
    @SerializedName("MANUAL03") val manual3: String?,
    @SerializedName("MANUAL_IMG03") val manual3_img: String?,
    @SerializedName("MANUAL04") val manual4: String?,
    @SerializedName("MANUAL_IMG04") val manual4_img: String?,
    @SerializedName("MANUAL05") val manual5: String?,
    @SerializedName("MANUAL_IMG05") val manual5_img: String?,
    @SerializedName("MANUAL06") val manual6: String?,
    @SerializedName("MANUAL_IMG06") val manual6_img: String?,
    @SerializedName("MANUAL07") val manual7: String?,
    @SerializedName("MANUAL_IMG07") val manual7_img: String?,
    @SerializedName("MANUAL08") val manual8: String?,
    @SerializedName("MANUAL_IMG08") val manual8_img: String?,
    @SerializedName("MANUAL09") val manual9: String?,
    @SerializedName("MANUAL_IMG09") val manual9_img: String?,
    @SerializedName("MANUAL10") val manual10: String?,
    @SerializedName("MANUAL_IMG10") val manual10_img: String?,
    @SerializedName("MANUAL11") val manual11: String?,
    @SerializedName("MANUAL_IMG11") val manual11_img: String?,
    @SerializedName("MANUAL12") val manual12: String?,
    @SerializedName("MANUAL_IMG12") val manual12_img: String?,
    @SerializedName("MANUAL13") val manual13: String?,
    @SerializedName("MANUAL_IMG13") val manual13_img: String?,
    @SerializedName("MANUAL14") val manual14: String?,
    @SerializedName("MANUAL_IMG14") val manual14_img: String?,
    @SerializedName("MANUAL15") val manual15: String?,
    @SerializedName("MANUAL_IMG15") val manual15_img: String?,
    @SerializedName("MANUAL16") val manual16: String?,
    @SerializedName("MANUAL_IMG16") val manual16_img: String?,
    @SerializedName("MANUAL17") val manual17: String?,
    @SerializedName("MANUAL_IMG17") val manual17_img: String?,
    @SerializedName("MANUAL18") val manual18: String?,
    @SerializedName("MANUAL_IMG18") val manual18_img: String?,
    @SerializedName("MANUAL19") val manual19: String?,
    @SerializedName("MANUAL_IMG19") val manual19_img: String?,
    @SerializedName("MANUAL20") val manual20: String?,
    @SerializedName("MANUAL_IMG20") val manual20_img: String?
)
