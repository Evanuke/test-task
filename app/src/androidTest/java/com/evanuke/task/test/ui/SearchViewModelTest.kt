package com.evanuke.task.test.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.evanuke.task.test.MainCoroutineRule
import com.evanuke.task.test.data.repository.SearchRepository
import com.evanuke.task.test.runBlockingTest
import com.nhaarman.mockito_kotlin.doReturn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.mockito.Mock
import javax.inject.Inject

@HiltAndroidTest
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var viewStateObserver: Observer<SearchRepositoryState>

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        viewModel = SearchViewModel(searchRepository)
        viewModel.repositories.observeForever(viewStateObserver)
    }

    @Test
    fun xaxa() = coroutineRule.runBlockingTest {
//        doReturn()
    }
}
