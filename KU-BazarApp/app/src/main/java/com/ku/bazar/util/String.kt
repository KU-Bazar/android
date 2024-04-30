package com.ku.bazar.util

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

class String{

    val firstTitlefirst = buildAnnotatedString {
        append("Easy")
        append("\t")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("Registration")
        }
    }

    val firstDescriptionfirst = buildAnnotatedString {
        append("With")
        append("\t")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("oAuth")
        }
        append("\t")
        append("the registration")
    }

    val firstDescriptionsecond =buildAnnotatedString {
        append("would be hassle free.")
    }

    val secondTitlefirst = buildAnnotatedString {
        append("Buy or Sell")
        append("\t")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("Products")
        }
    }

    val secondDescriprtionFirst = buildAnnotatedString {
        append("You can sell")
        append("\t")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("anything")
        }
        append("\t")
        append("including")
    }

    val secondDescriptionSecond = buildAnnotatedString {
        append("study resources,furniture,")
        append("\n")
        append("even your friend ;)")
    }


}