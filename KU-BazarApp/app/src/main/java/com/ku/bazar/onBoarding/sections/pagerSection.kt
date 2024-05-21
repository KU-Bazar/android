package com.ku.bazar.onBoarding.sections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.ku.bazar.onBoarding.screen.firstScreen
import com.ku.bazar.onBoarding.screen.secondScreen
import com.ku.bazar.onBoarding.screen.thirdScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun pagerSection(
    state: PagerState
){

    HorizontalPager(
        state = state
    ) {page ->
        when(page){
            0 -> firstScreen()
            1 -> secondScreen()
            2 -> thirdScreen()
            //else -> send to error page;
        }
    }
}
