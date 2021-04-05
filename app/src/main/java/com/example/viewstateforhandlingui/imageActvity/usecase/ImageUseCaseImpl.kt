package com.example.viewstateforhandlingui.imageActvity.usecase

import com.example.viewstateforhandlingui.imageActvity.repo.remote.ImageRepo
import com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel.ImageResponse
import io.reactivex.Single

class ImageUseCaseImpl(private val imageRepo: ImageRepo): ImageUseCase {
    /**
     * This method simply returns the response received from repo
     */
    override fun getImageResponse(): Single<ImageResponse> {
        return imageRepo.getImage()
    }
}