package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.myanmarlinks.retrofittest.network.ApiService

class MainViewModelFactory(private val api: ApiService): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }

}