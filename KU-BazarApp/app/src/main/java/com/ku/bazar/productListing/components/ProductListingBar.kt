package com.ku.bazar.productListing.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ku.bazar.R
import com.ku.bazar.ui.theme.PrimaryPink
import com.ku.bazar.ui.theme.SecondaryPink
import com.ku.bazar.ui.theme.TextBlack
import com.ku.bazar.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListingBar(heading: String,subheading:String){
    CenterAlignedTopAppBar(
        title = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = heading,
                    style = TextStyle(
                        color = White,
                        fontSize = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = subheading,
                    style = TextStyle(
                        color = White,
                        fontSize = 10.sp,
                    )
                )
            }
        },
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = SecondaryPink
        ),
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null,
                    tint = Color(0xFFCCCCCC),
                    modifier = Modifier.size(10.dp)
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}