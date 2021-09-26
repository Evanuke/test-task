package com.evanuke.task.test.data.model

import com.google.gson.annotations.SerializedName


data class SearchRepositoriesResponse(
    @SerializedName("items") val items: List<Repository>
) {

    data class Repository(
        @SerializedName("id") val id: Int,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("description") val description: String?,
        @SerializedName("language") val language: String?,
        @SerializedName("stargazers_count") val stargazersCount: Int
    )
}
