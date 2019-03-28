package com.example.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposViewModel : AndroidViewModel {

    constructor(application: Application) : super(application)

    val liveData = MutableLiveData<ReposResult>()
    private val myJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + myJob)
    var context:Context=getApplication()
    var userValues = this@ReposViewModel.context.applicationContext as UserModel
    var login:String=userValues.getLogin()

    fun getRepos() {
        liveData.value = ReposResult.ShowLoading
        uiScope.launch {
            val list = withContext(Dispatchers.IO) {
                RetrofitService.getReposApi().getReposCoroutine(login)
            }.await()
            liveData.value = ReposResult.ReposList(list)
            liveData.value = ReposResult.HideLoading

        }

    }
}



    sealed class ReposResult {
        object ShowLoading : ReposResult()
        object HideLoading : ReposResult()
        data class ReposList(val list: List<ReposModel>?) : ReposResult()
        data class Error(val error: String) : ReposResult()
    }
