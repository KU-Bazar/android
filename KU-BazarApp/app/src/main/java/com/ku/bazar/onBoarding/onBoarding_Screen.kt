package com.ku.bazar.onBoarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ku.bazar.onBoarding.sections.bottomSection
import com.ku.bazar.onBoarding.sections.pagerSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun onBoarding(){

    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(pageCount = { 0 })
    val showBackButton = pageState.currentPage > 0

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(0.15f)){
            topSection(
                showBackButton = showBackButton,
                onBackClicked = {
                    if(pageState.currentPage +1 >1)
                        scope.launch {
                            pageState.scrollToPage(pageState.currentPage - 1)
                        }
                }
            )
        }

        Box(modifier = Modifier.weight(0.65f)){
            pagerSection(
                state = pageState,
                pageStage = pageState.currentPage
            )
        }

        Box(modifier = Modifier.weight(0.2f)){
            bottomSection(
                onArrowClick = {scope.launch {
                    pageState.scrollToPage(pageState.currentPage - 1)
                }},
                skipClicked = { /*TODO*/ },
                isSelected = true,
                indicatorHeight = 10.dp,
                selectedIndicatorColor = Color.Red,
                unselectedIndicatorColor = Color.Black
            )
        }
    }
}