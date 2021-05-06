package com.example.mycinema.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycinema.model.Review
import com.example.mycinema.model.Video
import com.example.mycinema.network.DataFetchStatus
import com.example.mycinema.network.MovieResponse
import com.example.mycinema.network.ReviewResponse
import com.example.mycinema.network.VideoResponse
import com.example.mycinema.utils.Constants
import kotlinx.coroutines.launch

class ThirdViewModel(application: Application) : AndroidViewModel(application){
    private val _dataFetchStatus = MutableLiveData<DataFetchStatus>()
    val dataFetchStatus: LiveData<DataFetchStatus>
        get() {
            return _dataFetchStatus
        }


    private val _reviewList = MutableLiveData<List<Review>>()
    val reviewList: LiveData<List<Review>>
        get() {
            return _reviewList
        }

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>>
        get() {
            return _videoList
        }

    init {
        _dataFetchStatus.value = DataFetchStatus.LOADING
    }

    fun getReviews(id: Int){
        viewModelScope.launch {
            try {
                val reviewResponse: ReviewResponse =
                    TMDBApi.movieListRetrofitService.getReviews(movieId = id)
                _reviewList.value = reviewResponse.results
                _dataFetchStatus.value = DataFetchStatus.DONE
            } catch(e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _reviewList.value = arrayListOf()
            }
        }
    }

    fun getVideos(id: Int){
        viewModelScope.launch{
            try{
                val videoResponse: VideoResponse =
                    TMDBApi.movieListRetrofitService.getVideos(movieId = id)
                    _videoList.value = videoResponse.results
                    _dataFetchStatus.value = DataFetchStatus.DONE
            } catch(e: Exception) {
                _dataFetchStatus.value = DataFetchStatus.ERROR
                _videoList.value = arrayListOf()
            }
        }
    }


}