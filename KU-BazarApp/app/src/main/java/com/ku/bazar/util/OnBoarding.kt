package com.ku.bazar.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import com.ku.bazar.R


val stringObject = String()
sealed class OnBoarding(
    @DrawableRes  //This tells the compiler that the return value of an integer parameter will be a drawable resource
    val image: Int,
    val titleFirst: AnnotatedString,
    val description_first: AnnotatedString,
    val description_second: AnnotatedString,
    @DrawableRes
    val backFirst: Int,
    @DrawableRes
    val backSecond: Int,
    @DrawableRes
    val backThird: Int,
    @DrawableRes
    val backFourth: Int
){
    object First : OnBoarding(
        image = R.drawable.onboard_me,
        titleFirst = stringObject.firstTitlefirst,
        description_first = stringObject.firstDescriptionfirst,
        description_second = stringObject.firstDescriptionsecond,
        backFirst = R.drawable.wall,
        backSecond = R.drawable.clock,
        backThird = R.drawable.another_wall,
        backFourth = R.drawable.picture
    )

    object Second : OnBoarding(
        image = R.drawable.onboard_second,
        titleFirst = stringObject.secondTitlefirst,
        description_first = stringObject.secondDescriprtionFirst,
        description_second = stringObject.secondDescriptionSecond,
        backFirst = R.drawable.clock_second,
        backSecond = R.drawable.picture_second,
        backThird = R.drawable.wall,
        backFourth = R.drawable.another_wall
    )
}
