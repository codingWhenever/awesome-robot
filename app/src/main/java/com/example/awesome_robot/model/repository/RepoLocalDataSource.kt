package com.example.awesome_robot.model.repository

import android.os.Handler
import com.example.awesome_robot.Repository

class RepoLocalDataSource {
    fun getRepositories(callback: OnRepoLocalReadyCallback) {
        var arrayList = ArrayList<Repository>()
        arrayList.add(Repository("First", "Owner 1", 100, false))
        arrayList.add(Repository("Second", "Owner 2", 30, true))
        arrayList.add(Repository("Third", "Owner 3", 520, false))
        Handler().postDelayed({ callback.onLocalDataReady(arrayList) }, 3000)
    }


    fun saveRepositories(arrayLis: ArrayList<Repository>) {

    }

    interface OnRepoLocalReadyCallback {
        fun onLocalDataReady(dataSource: ArrayList<Repository>)
    }
}