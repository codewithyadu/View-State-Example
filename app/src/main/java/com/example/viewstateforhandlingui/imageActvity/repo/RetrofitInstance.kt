package com.example.viewstateforhandlingui.imageActvity.repo

import com.example.viewstateforhandlingui.imageActvity.repo.remote.requestmodel.ImageRequest

interface RetrofitInstance {
    fun getImageApiRequestInstance(): ImageRequest
}