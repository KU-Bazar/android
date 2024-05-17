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
    val description_third: kotlin.String,
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
        description_third = stringObject.firstDescriptionThird,
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
        description_third = stringObject.secondDescriptionThird,
        backFirst = R.drawable.clock_second,
        backSecond = R.drawable.picture_second,
        backThird = R.drawable.wall,
        backFourth = R.drawable.another_wall
    )

    object Third : OnBoarding(
        image = R.drawable.onboard_third,
        titleFirst = stringObject.thirdTitleFirst,
        description_first = stringObject.thirdDescriptionFirst,
        description_second = stringObject.thirdDescriptionSecond,
        description_third = stringObject.thirdDescriptionThird,
        backFirst = R.drawable.picture_third,
        backSecond = R.drawable.wall,
        backThird = R.drawable.another_wall,
        backFourth = R.drawable.clock  // This not used but using Int? gave an error in Onbording screen
    )
}

sealed class topSection(
    @DrawableRes
    val backImage : Int
){

}
