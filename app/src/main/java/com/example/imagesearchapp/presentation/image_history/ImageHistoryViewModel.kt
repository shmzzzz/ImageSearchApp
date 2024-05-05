package com.example.imagesearchapp.presentation.image_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.domain.model.ImageHistory
import com.example.imagesearchapp.domain.repository.ImageHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageHistoryViewModel @Inject constructor(
    private val repository: ImageHistoryRepository
) : ViewModel() {
    private val _historyList = MutableStateFlow<List<ImageHistory>>(emptyList())
    val historyList = _historyList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().distinctUntilChanged().collect { listOfHistory ->
                if (listOfHistory.isEmpty()) {
                    _historyList.value = emptyList()
                } else {
                    _historyList.value = listOfHistory
                }
            }
        }
    }

    fun insertHistory(imageHistory: ImageHistory) =
        viewModelScope.launch {
            repository.insert(imageHistory)
        }

    fun updateHistory(imageHistory: ImageHistory) =
        viewModelScope.launch {
            repository.update(imageHistory)
        }

    fun deleteHistory(imageId: String) = viewModelScope.launch {
        repository.deleteHistoryById(imageId)
    }
}
