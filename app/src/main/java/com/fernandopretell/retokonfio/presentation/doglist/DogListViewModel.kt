package com.fernandopretell.retokonfio.presentation.doglist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernandopretell.retokonfio.domain.model.Dog
import com.fernandopretell.retokonfio.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val getDogs: GetDogsUseCase
) : ViewModel() {

    private val _dogs = mutableStateOf<List<Dog>>(emptyList())
    val dogs: State<List<Dog>> get() = _dogs

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> get() = _errorMessage

    init {
        viewModelScope.launch {
            try {
                _dogs.value = getDogs()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}

