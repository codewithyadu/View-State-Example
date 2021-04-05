package com.example.viewstateforhandlingui.imageActvity.repo

import com.example.viewstateforhandlingui.imageActvity.repo.remote.requestmodel.ImageRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceImpl : RetrofitInstance {
    lateinit var retrofit: Retrofit

    private fun setRetrofitInstance() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    override fun getImageApiRequestInstance(): ImageRequest{
        setRetrofitInstance()
        return retrofit.create(ImageRequest::class.java)
    }

}