package com.example.viewstateforhandlingui.imageActvity.repo.remote

import com.example.viewstateforhandlingui.imageActvity.repo.RetrofitInstanceImpl
import com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel.ImageResponse
import io.reactivex.Single

class ImageRepoImpl() : ImageRepo {
    /**
     * This method makes the api call using retrofit and returns response
     * inside single of rxJava
     */
    override fun getImage(): Single<ImageResponse> {
        val request = RetrofitInstanceImpl.getImageApiRequestInstance()
        return request.getSalonFeatures(1)
    }
}