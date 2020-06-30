package com.example.awesome_robot.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.awesome_robot.Repository
import com.example.awesome_robot.model.RepoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var repoRepository: RepoRepository = RepoRepository(getApplication())
    val text = ObservableField<String>("old data")
    val isLoading = ObservableField<Boolean>(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()
    var onDataReadyCallback = object : RepoRepository.OnDataReadyCallback {
        override fun onDataReady(data: String) {
            isLoading.set(false)
            text.set(data)
        }
    }

    fun refresh() {
        isLoading.set(true)
        repoRepository.refreshData(onDataReadyCallback)
    }

    fun loadRepositories() {
        isLoading.set(true)
        repoRepository.getRepositories(object : RepoRepository.OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.postValue(data)
            }
        })
    }
}