package com.example.viewstateforhandlingui.imageActvity.repo.remote

import com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel.ImageResponse
import io.reactivex.Single

interface ImageRepo {
    fun getImage(): Single<ImageResponse>
}