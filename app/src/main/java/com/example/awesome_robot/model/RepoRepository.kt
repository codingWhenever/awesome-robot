package com.example.awesome_robot.model

import android.content.Context
import android.os.Handler
import com.example.awesome_robot.Repository
import com.example.awesome_robot.manager.NetManager
import com.example.awesome_robot.model.repository.RepoLocalDataSource
import com.example.awesome_robot.model.repository.RepoRemoteDataSource

class RepoRepository(context: Context) {
    val localDataSource = RepoLocalDataSource()
    private val remoteDataSource = RepoRemoteDataSource()
    private val netManager = NetManager(context)
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed(
            { onDataReadyCallback.onDataReady("new data") },
            3000
        )
    }


    fun getRepositories(onRepositoryReadyCallback: OnRepositoryReadyCallback) {
        netManager.isConnected2Internet?.let {
            if (it) {
                remoteDataSource.getRepositories(object :
                    RepoRemoteDataSource.OnRepoRemoteReadyCallback {
                    override fun onRemoteDataReady(data: ArrayList<Repository>) {
                        localDataSource.saveRepositories(data)
                        onRepositoryReadyCallback.onDataReady(data)
                    }
                })
            } else {
                localDataSource.getRepositories(object :
                    RepoLocalDataSource.OnRepoLocalReadyCallback {
                    override fun onLocalDataReady(dataSource: ArrayList<Repository>) {
                        onRepositoryReadyCallback.onDataReady(dataSource)
                    }
                })
            }
        }

    }

    interface OnDataReadyCallback {
        fun onDataReady(data: String)
    }

    interface OnRepositoryReadyCallback {
        fun onDataReady(data: ArrayList<Repository>)
    }
}