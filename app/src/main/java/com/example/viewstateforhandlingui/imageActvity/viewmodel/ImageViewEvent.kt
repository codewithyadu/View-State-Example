package com.example.viewstateforhandlingui.imageActvity.viewmodel

sealed class ImageViewEvents {
    data class ImageDownload(var isImageDownloaded: Boolean): ImageViewEvents()
}