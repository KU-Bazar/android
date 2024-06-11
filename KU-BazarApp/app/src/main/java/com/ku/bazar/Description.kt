package com.ku.bazar

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.ArrowBack

import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft

import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White



@Preview
@Composable
fun Description()

{

        val images = listOf(
            R.drawable.guitar,
            R.drawable.ic_tv,
            R.drawable.ic_books,

        )



    Box(modifier = Modifier.fillMaxSize()


            )

    {


        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    )
                )
        )



        Box(modifier = Modifier
            .fillMaxSize()


            .offset(y = 90.dp, x = 120.dp)



        )
        {
            Image(painter = painterResource(id = R.drawable.topographic_6), contentDescription = "Pattern",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()



            )



        }








        Box(modifier = Modifier
            .fillMaxWidth()
            //.clickable { }
            .align(Alignment.TopStart)
            .padding(start = 30.dp, top = 40.dp)
        )

        {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(0xFFF5F5F5))
                    .padding(10.dp),
                tint = Color.Black
            )
        }


                Box(
                    modifier = Modifier
                        .width(310.dp)
                        .align(Alignment.CenterEnd)
                        .height(330.dp)
                        .offset(y = (-120).dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 100.dp
                            )
                        )





                ) {

                    LazyRow {
                        items(images) { imageRes ->
                            Box(
                                modifier = Modifier
                                    .width(310.dp)
                                    .height(330.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    PrimaryPink.copy(alpha = 0f),
                                                    PrimaryPink.copy(alpha = 0f),
                                                    PrimaryPink.copy(alpha = 0f),
                                                    PrimaryPink.copy(alpha = 0f),
                                                    White.copy(alpha = 1f),
                                                )
                                            )
                                        )
                                )
                            }
                        }
                    }
                }




            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = 100.dp, x = 340.dp)



        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favourite_unfill),
                contentDescription = "Favorite Icon",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .background(color = Color(0xFFF5F5F5))
                    .padding(8.dp)
            )


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .offset(y = 100.dp, x = 10.dp)
        )
        {
            Text(text = "Buy Acoustic Guitar", fontSize = 20.sp,modifier = Modifier
                .padding(top = 50.dp))
            Text(
                text = "Rs.1800",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 80.dp)
            )

            Row (modifier = Modifier.padding(top = 110.dp)){


                Button(
                    onClick = { /* TODO: Handle buy now click */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = PrimaryPink,
                        contentColor = Color.White

                    ),
                    shape = RoundedCornerShape(20.dp),
                  //  modifier = Modifier.padding(top = 110.dp)
                ) {
                    Text(text = "Chat  Now", modifier = Modifier.padding(end = 12.dp))
                    Icon(
                        imageVector = Icons.Filled.MailOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(color = Color(0xFFF5F5F5))
                            .padding(5.dp),
                        tint = PrimaryPink
                    )

                }


                Box(
                    modifier= Modifier
                        .width(130.dp)
                        .height(40.dp)
                       // .offset(y = 100.dp, x = 10.dp)
                        .padding(start = 5.dp,top=5.dp)
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(20.dp)
                        )

                ) {
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_instruments),
                            contentDescription = "Category Icon",
                            modifier = Modifier
                                .size(24.dp) // Adjust size as needed
                                .clip(CircleShape)
                                .background(PrimaryPink)
                                .padding(4.dp), // Adjust padding as needed
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Instruments",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextBlack
                        )
                    }
                }

            }


        }











        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)
            .offset(y = 240.dp, x = 10.dp))
        {
            Text(
                text = "Product Description",
                fontSize = 16.sp,

                modifier = Modifier
                   // .padding(top = 20.dp)
            )


            Text(
                text = "Band:yamaha",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 30.dp)
            )
            Text(
                text = "Model:XYZ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 60.dp)
            )
            Text(
                text = "Color:Grey",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 90.dp)
            )








        }




    }


    }









