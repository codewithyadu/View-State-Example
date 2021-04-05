package com.example.viewstateforhandlingui.imageActvity.usecase

import com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel.ImageResponse
import io.reactivex.Single

interface ImageUseCase {
    fun getImageResponse(): Single<ImageResponse>
}