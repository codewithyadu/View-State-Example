package com.example.viewstateforhandlingui.imageActvity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.viewstateforhandlingui.R
import com.example.viewstateforhandlingui.databinding.ActivityMainBinding
import com.example.viewstateforhandlingui.imageActvity.repo.remote.ImageRepoImpl
import com.example.viewstateforhandlingui.imageActvity.usecase.ImageUseCase
import com.example.viewstateforhandlingui.imageActvity.usecase.ImageUseCaseImpl
import com.example.viewstateforhandlingui.imageActvity.viewmodel.ImageViewModel

class ImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            lifecycleOwner = this@ImageActivity
            imageActivity = this@ImageActivity
        }
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        observeViewState()
    }

    private fun observeViewState() {
        imageViewModel.viewState.observe(this, Observer {
            with(binding) {
                //handle loading view state
                if (it.isLoading) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
                //setImageUrl if response is successful else show error toast
                if (it.isResponseSuccessful) {
                    Glide.with(this@ImageActivity)
                            .load(it.imageUrl)
                            .into(imageView);
                }
            }
        })
    }

    fun showImage() {
        val imageRepo = ImageRepoImpl()
        val imageUseCase = ImageUseCaseImpl(imageRepo)
        imageViewModel.getImage(imageUseCase)
    }

    override fun onDestroy() {
        super.onDestroy()
        imageViewModel.clearDisposable()
    }
}