package com.example.viewstateforhandlingui.imageActvity.viewmodel

data class ImageViewState(
    val isLoading: Boolean = false,
    val isResponseSuccessful: Boolean = false,
    val imageUrl: String = "",
)