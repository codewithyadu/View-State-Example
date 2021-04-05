package com.example.viewstateforhandlingui.imageActvity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewstateforhandlingui.imageActvity.usecase.ImageUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel() : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _viewState: MutableLiveData<ImageViewState> = MutableLiveData()
    val viewState: LiveData<ImageViewState> = _viewState

    init {
        _viewState.value = ImageViewState()
    }

    fun getImage(imageUseCase: ImageUseCase) {
        val newViewState = viewState.value?.copy(isLoading = true)
        _viewState.value = newViewState
        val disposable = imageUseCase.getImageResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val successViewState = viewState.value?.copy(
                        isLoading = false,
                        isResponseSuccessful = true,
                        imageUrl = it[0].img
                    )
                    _viewState.value = successViewState
                    Log.d("response", it.toString())
                },
                {
                    val errorViewState = viewState.value?.copy(
                        isLoading = false,
                        isResponseSuccessful = false
                    )
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