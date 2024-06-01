package com.ku.bazar.login.util

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

class String{

    val loginHeading = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("Everything")
        }
        append("\t")
        append("you need in")
        append("\n")
        append("one place")
    }

    val loginDescription = buildAnnotatedString {
        append("find your daily necessity, furnitures,")
        append("\n")
        append("second hand stuffs or even brand")
        append("\n")
        append("new items near")
        append("\t")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
            append("Kathmandu")
            append("\n")
            append("University.")
        }
    }
}