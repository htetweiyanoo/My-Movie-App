package net.myanmarlinks.retrofittest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.myanmarlinks.retrofittest.DependencyProvider

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(DependencyProvider.getMovieDetailRepository()) as T
    }
}