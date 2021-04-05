package com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel


import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("appearance")
    val appearance: List<Int>,
    @SerializedName("better_call_saul_appearance")
    val betterCallSaulAppearance: List<Any>,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("char_id")
    val charId: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("occupation")
    val occupation: List<String>,
    @SerializedName("portrayed")
    val portrayed: String,
    @SerializedName("status")
    val status: String
)