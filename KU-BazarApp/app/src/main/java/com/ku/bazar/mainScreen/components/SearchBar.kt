package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ku.bazar.R
import com.ku.bazar.ui.theme.Dimension

@Composable
fun SearchBar(
    value: String,
    onValueChange: (value: String) -> Unit,
    onFocusChange: (hadFocus: Boolean) -> Unit,
    onImeActionClicked: KeyboardActionScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomInputField(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            value = value,
            onValueChange = onValueChange,
            placeholder = "What are you looking for?",
            textStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
            padding = PaddingValues(
                horizontal = Dimension.pagePadding,
                vertical = Dimension.pagePadding.times(0.7f)
            ),
            backgroundColor = Color(0xFFF5F5F5),
            textColor = Color(0xFFA3A3A3),
            imeAction = ImeAction.Search,
            shape = MaterialTheme.shapes.large,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                )
            },
            onFocusChange = onFocusChange,
            onKeyboardActionClicked = onImeActionClicked,
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
        Icon(
            modifier = Modifier
                .size(24.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_filter), // Add your filter icon here
            contentDescription = null,
           // tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
        )
    }
}