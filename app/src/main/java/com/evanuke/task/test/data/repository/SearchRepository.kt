package com.evanuke.task.test.data.repository

import com.evanuke.task.test.data.model.SearchRepositoriesResponse
import com.evanuke.task.test.network.ApiInterface
import com.evanuke.task.test.ui.RepositoryUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getRepositories(query: String) = flow {
        val response = withContext(Dispatchers.IO) {
            apiInterface.getRepositoriesByQuery(query)
        }

        emit(mapToRepositoryUiModel(response))
    }

    private fun mapToRepositoryUiModel(response: SearchRepositoriesResponse) = response.items.map {
        RepositoryUiModel(
            id = it.id,
            name = it.fullName,
            url = it.htmlUrl,
            description = it.description,
            language = it.language,
            stars = it.stargazersCount
        )
    }
}
