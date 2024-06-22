package com.ku.bazar.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ku.bazar.ui.theme.Dimension

@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    textColor: Color = Color(0xFFA3A3A3),
    backgroundColor: Color = Color(0xFFF5F5F5),
    requireSingleLine: Boolean = true,
    textShouldBeCentered: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = Int.MAX_VALUE,
    imeAction: ImeAction = ImeAction.Done,
    shape: Shape = MaterialTheme.shapes.small,
    padding: PaddingValues = PaddingValues(horizontal = Dimension.xs),
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (string: String) -> Unit,
    onFocusChange: (focused: Boolean) -> Unit,
    onKeyboardActionClicked: KeyboardActionScope.() -> Unit,

) {
    val focusRequester = remember { FocusRequester() }
    val inputService = LocalTextInputService.current

    Row(
        modifier = modifier
            .clip(shape = shape)
            .background(backgroundColor)
            .padding(paddingValues = padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding.div(2))
    ) {
        leadingIcon()
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    onFocusChange(it.isFocused)
                },
            value = value,
            onValueChange = {
                if (it.length <= maxLength) {
                    /** when the value change and maxLength is not reached yet then pass it up **/
                    /** when the value change and maxLength is not reached yet then pass it up **/
                    /** when the value change and maxLength is not reached yet then pass it up **/
                    /** when the value change and maxLength is not reached yet then pass it up **/
                    onValueChange(it)
                }
            },
            decorationBox = { container ->
                Box(
                    contentAlignment = if (textShouldBeCentered) Alignment.Center else Alignment.CenterStart,
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = textColor.copy(alpha = 0.3f),
                            maxLines = if (requireSingleLine) 1 else Int.MAX_VALUE,
                        )
                    }
                    container()
                }
            },
            visualTransformation = visualTransformation,
            singleLine = requireSingleLine,
            textStyle = textStyle.copy(color = textColor),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions(
                onAny = {
                    focusRequester.freeFocus()
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    /** It doesn't has the focus now, hide the input keyboard */
                    inputService?.hideSoftwareKeyboard()
                    onKeyboardActionClicked()
                }
            ),
            cursorBrush = SolidColor(value = textColor),
        )
        trailingIcon()
    }
}