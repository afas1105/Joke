package com.example.jokemvp.model.response

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("error")
    val error: Boolean?,

    @SerializedName("amount")
    val amount: Int?,

    @SerializedName("jokes")
    val jokes: List<JokesData>
)

data class JokesData(
    @SerializedName("category")
    val category: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("joke")
    val joke: String?,

    @SerializedName("flags")
    val flags: FlagData,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("safe")
    val safe: Boolean?,

    @SerializedName("lang")
    val lang: String?
)

data class FlagData(
    @SerializedName("sansfwfe")
    val snsfwafe: Boolean?,

    @SerializedName("religious")
    val religious: Boolean?,

    @SerializedName("political")
    val political: Boolean?,

    @SerializedName("racist")
    val racist: Boolean?,

    @SerializedName("sexist")
    val sexist: Boolean?,

    @SerializedName("explicit")
    val explicit: Boolean?
)
