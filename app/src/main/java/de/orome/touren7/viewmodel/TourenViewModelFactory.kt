package de.orome.touren7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.orome.touren7.model.repositories.TourenRepository
import java.lang.IllegalArgumentException

class TourenViewModelFactory(private val repository: TourenRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourenViewModel::class.java)){
            return TourenViewModel(repository) as T
        }
        throw IllegalArgumentException("UnknownViewModel Class")
    }
}