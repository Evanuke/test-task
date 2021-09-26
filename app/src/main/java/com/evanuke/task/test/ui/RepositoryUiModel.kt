package com.evanuke.task.test.ui

data class RepositoryUiModel(
    val id: Int,
    val name: String,
    val url: String,
    val description: String? = null,
    val language: String? = null,
    val stars: Int
)
