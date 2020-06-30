package com.example.awesome_robot.model.repository

import android.os.Handler
import com.example.awesome_robot.Repository

class RepoRemoteDataSource {
    fun getRepositories(callback: OnRepoRemoteReadyCallback) {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First from remote", "Owner 1", 100, false))
        arrayList.add(Repository("Second from remote", "Owner 2", 30, true))
        arrayList.add(Repository("Third from remote", "Owner 3", 430, false))

        Handler().postDelayed({ callback.onRemoteDataReady(arrayList) }, 2000)
    }

    interface OnRepoRemoteReadyCallback {
        fun onRemoteDataReady(dat: ArrayList<Repository>)
    }
}