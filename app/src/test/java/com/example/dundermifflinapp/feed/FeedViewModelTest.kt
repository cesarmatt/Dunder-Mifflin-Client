package com.example.dundermifflinapp.feed

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dundermifflinapp.data.feed.FeedRepository
import com.example.dundermifflinapp.ui.feed.FeedViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.nullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class FeedViewModelTest {

    private lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        feedViewModel = FeedViewModel(
            mock(FeedRepository::class.java)
        )
    }

    @Test
    fun getOrder_returnOrders() {
        feedViewModel.getOrders()

        val value = feedViewModel.orderViews.value
        assertThat(value, nullValue())
    }
}