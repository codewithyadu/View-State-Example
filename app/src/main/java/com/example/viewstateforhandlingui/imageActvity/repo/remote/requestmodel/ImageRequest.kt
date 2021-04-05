package com.example.viewstateforhandlingui.imageActvity.repo.remote.requestmodel

import com.example.viewstateforhandlingui.imageActvity.repo.remote.responsemodel.ImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageRequest {
    @GET("characters")
    fun getSalonFeatures(
        @Query("limit") limit: Int
    ): Single<ImageResponse>
}