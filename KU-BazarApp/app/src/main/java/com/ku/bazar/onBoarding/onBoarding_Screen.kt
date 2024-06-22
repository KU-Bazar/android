package com.ku.bazar.onBoarding

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ku.bazar.navigation.Screen
import com.ku.bazar.onBoarding.component.OnBoardingViewModel
import com.ku.bazar.onBoarding.sections.bottomSection
import com.ku.bazar.onBoarding.sections.pagerSection
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onBoarding(navController: NavHostController, viewModel: OnBoardingViewModel){

    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(initialPage = 0, pageCount = { 3 } )
    val showBackButton = pageState.currentPage > 0

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(0.1f)){
            topSection(
                showBackButton = showBackButton,
                onBackClicked = {
                    if(pageState.currentPage > 0) {
                        scope.launch {
                            pageState.scrollToPage(pageState.currentPage - 1)
                        }
                    }
                }
            )
        }

        Box(modifier = Modifier.weight(0.8f)){
            pagerSection(
                state = pageState
            )
        }

        Box(
            modifier = Modifier
                .weight(0.1f)
                .padding(horizontal = 60.dp)
        ){
            bottomSection(
                onArrowClick = {
                    scope.launch {
                        if(pageState.currentPage == 2) {
                            viewModel.setFirstTimeStatus(false)
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.OnBoarding.route) { inclusive = true }
                            }
                        }
                        if (pageState.currentPage < pageState.pageCount - 1) {
                            pageState.scrollToPage(pageState.currentPage + 1)
                        }
                        val test = pageState.currentPage
                        val test1 = pageState.pageCount
                        Log.d("Pagestate","Current page $test")
                        Log.d("Pagestate","page Count $test1")
                    }},
                skipClicked = {
                    if (pageState.currentPage == pageState.pageCount - 1) {
                        viewModel.setFirstTimeStatus(false)
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.OnBoarding.route) { inclusive = true }
                        }
                    }	else {
                        scope.launch {
                            pageState.scrollToPage(pageState.pageCount - 1)
                        }
                    }
                },
                currentPage = pageState.currentPage,
                indicatorHeight = 10.dp,
                selectedIndicatorColor = PrimaryPink,
                unselectedIndicatorColor = SecondaryPink
            )
        }
    }
}