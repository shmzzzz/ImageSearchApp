package com.example.imagesearchapp.presentation.search_images.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onDone: () -> Unit,
    placeholderText: String = "Search...",
) {
    // 入力時に✗ボタンを表示させる
    var showClearButton by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .padding(bottom = 8.dp),
        backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .onFocusChanged { focusState ->
                    showClearButton = focusState.isFocused
                }
                .focusRequester(focusRequester),
            placeholder = {
                Text(
                    text = placeholderText,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    style = TextStyle(textAlign = TextAlign.Start),
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearchTextChanged("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close",
                    )
                }
            },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                onDone()
            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF03A9F4),
                unfocusedBorderColor = Color(0xFF03A9F4),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,  // 入力値のテキスト色
            ),
        )
    }
    // アプリが起動したときに検索窓にフォーカスが当たるようにする
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        searchText = "",
        onSearchTextChanged = {},
        onDone = {}
    )
}
