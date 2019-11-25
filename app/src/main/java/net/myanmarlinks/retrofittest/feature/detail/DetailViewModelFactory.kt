package net.myanmarlinks.retrofittest.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.myanmarlinks.retrofittest.DependencyProvider

class DetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            DependencyProvider.getMovieDetailRepository()
        ) as T
    }
}