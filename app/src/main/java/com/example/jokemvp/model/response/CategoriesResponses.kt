package com.example.jokemvp.model.response

import com.google.gson.annotations.SerializedName

data class CategoriesResponses(
    @SerializedName("error")
    val error: Boolean?,

    @SerializedName("categories")
    val categories: List<String>?,

    @SerializedName("categoryAliases")
    val categoryAliases: List<AliasesData>?,

    @SerializedName("timestamp")
    val timestamp: Long?,
)

data class AliasesData(
    @SerializedName("alias")
    val alias: String?,

    @SerializedName("resolved")
    val resolved: String?
)
