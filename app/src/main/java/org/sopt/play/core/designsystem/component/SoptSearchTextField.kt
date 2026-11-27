package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.Gray2
import org.sopt.play.core.designsystem.theme.Gray3
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White

@Composable
fun SoptSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "검색어를 입력하세요",
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    // 아이콘을 눌러도, 키보드 검색 버튼을 눌러도 똑같이 동작
    val search = {
        onSearch()
        focusManager.clearFocus()
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = SoptTypography.m18.copy(color = Gray5),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { search() }),
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = it.isFocused },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, RoundedCornerShape(12.dp))
                    .border(
                        width = 2.dp,
                        color = if (isFocused) Gray5 else Gray2,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = SoptTypography.m18,
                            color = Gray2,
                        )
                    }
                    innerTextField()
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "검색",
                    tint = Gray3,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = search),
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SoptSearchTextFieldPreview() {
    PlaySoptTheme {
        SoptSearchTextField(
            value = "",
            onValueChange = {},
            onSearch = {},
        )
    }
}
