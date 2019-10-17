package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.myanmarlinks.retrofittest.network.interceptor.ApiDetailService

class DetailViewModelFactory(private val api: ApiDetailService): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(api) as T
    }
}