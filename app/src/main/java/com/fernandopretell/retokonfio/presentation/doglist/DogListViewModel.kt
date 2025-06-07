package com.fernandopretell.retokonfio.presentation.doglist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernandopretell.retokonfio.domain.model.Dog
import com.fernandopretell.retokonfio.domain.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _dogs = mutableStateOf<List<Dog>>(emptyList())
    val dogs: State<List<Dog>> get() = _dogs

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        viewModelScope.launch {
            _dogs.value = repository.getDogs()
            _isLoading.value = false
        }
    }
}

