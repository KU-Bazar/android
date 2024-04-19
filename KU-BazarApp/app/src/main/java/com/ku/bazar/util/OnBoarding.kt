package com.ku.bazar.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ku.bazar.R

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
        titleFirst = buildAnnotatedString {
                                           append("Easy")
            append("\t")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("Registration")
            }
        },
        description_first = buildAnnotatedString {
                                           append("With")
            append("\t")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("oAuth")
            }
            append("\t")
            append("the registration")
        },
        description_second = buildAnnotatedString {
            append("would be hassle free.")
        },
        backFirst = R.drawable.wall,
        backSecond = R.drawable.clock,
        backThird = R.drawable.another_wall,
        backFourth = R.drawable.picture
    )
}
