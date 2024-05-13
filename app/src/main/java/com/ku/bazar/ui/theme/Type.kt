package com.ku.bazar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ku.bazar.R

val redditSansFamily = FontFamily(
    Font(R.font.redditsans_light, weight= FontWeight.Light),
    Font(R.font.redditsans_medium, weight= FontWeight.Medium),
    Font(R.font.redditsans_regular, weight= FontWeight.Normal),
    Font(R.font.redditsans_black, weight = FontWeight.Black)
);

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = redditSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(fontFamily= redditSansFamily, fontWeight= FontWeight.Normal, fontSize = 25.sp, letterSpacing = (1).sp),
    body2 = TextStyle(fontFamily = redditSansFamily, fontWeight = FontWeight.Light),
    h2 = TextStyle(fontFamily = redditSansFamily, fontWeight = FontWeight.Thin),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)