package com.example.viewstateforhandlingui.imageActvity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewstateforhandlingui.imageActvity.usecase.ImageUseCase
import com.example.viewstateforhandlingui.imageActvity.util.Event
import com.example.viewstateforhandlingui.imageActvity.viewmodel.ImageViewEvents.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _viewState: MutableLiveData<ImageViewState> = MutableLiveData()
    val viewState: LiveData<ImageViewState> = _viewState
    private val _viewEvents: MutableLiveData<Event<ImageDownload>> = MutableLiveData()
    val viewEvents: LiveData<Event<ImageDownload>> = _viewEvents

    init {
        _viewState.value = ImageViewState()
    }

    /**
     * This method handles get image response and update the view state
     * based on whether the response is successful or error response
     */
    fun getImage(imageUseCase: ImageUseCase) {
        val newViewState = viewState.value?.copy(isLoading = true)
        _viewState.value = newViewState
        val disposable = imageUseCase.getImageResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //Success block
                    val successViewState = viewState.value?.copy(
                        isLoading = false,
                        imageUrl = it[0].img
                    )
                    _viewEvents.value = Event(ImageDownload(isImageDownloaded = true))
                    _viewState.value = successViewState
                    Log.d("response", it.toString())
                },
                {
                    //Error block
                    val errorViewState = viewState.value?.copy(
                        isLoading = false,
                    )
                    _viewEvents.value = Event(ImageDownload(isImageDownloaded = false))
                    _viewState.value = errorViewState
                    Log.d("response", it.toString())
                }
            )
        compositeDisposable.add(disposable)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}