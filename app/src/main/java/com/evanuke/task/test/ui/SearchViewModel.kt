package com.evanuke.task.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evanuke.task.test.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _repositories = MutableLiveData<SearchRepositoryState>()
    val repositories: LiveData<SearchRepositoryState>
        get() = _repositories

    fun requestRepositories(query: String) {
        _repositories.value = SearchRepositoryState.Loading

        viewModelScope.launch {
            searchRepository.getRepositories(query)
                .catch {
                    _repositories.value = SearchRepositoryState.Error(it)
                    Timber.e(it)
                }
                .collect {
                    _repositories.value = SearchRepositoryState.Success(it)
                }
        }
    }
}

sealed class SearchRepositoryState {
    object Loading : SearchRepositoryState()
    data class Error(val throwable: Throwable): SearchRepositoryState()
    data class Success(val repositories: List<RepositoryUiModel>): SearchRepositoryState()
}
