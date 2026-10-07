package com.example.androidone

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.androidone.ui.theme.AndroidoneTheme
import com.example.androidone.ui.theme.Black
import com.example.androidone.ui.theme.Gray1
import com.example.androidone.ui.theme.Gray2
import com.example.androidone.ui.theme.Gray3
import com.example.androidone.ui.theme.Gray5
import com.example.androidone.ui.theme.Red
import com.example.androidone.ui.theme.SoptTypography
import com.example.androidone.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    androidone(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun androidone(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "로그인 성공 ! 메인 화면이에요", style = SoptTypography.sb16, color = Black)
    }
}

@Composable
fun SoptTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    isPassword: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false)}

    val borderColor = when {
        isError -> Red
        isFocused -> Gray5
        else -> Gray2
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = SoptTypography.m14,
            color = Black,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = SoptTypography.m18.copy(color = Black),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                .padding(16.dp)
                .onFocusChanged { isFocused = it.isFocused },
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(text = placeholder, style = SoptTypography.m18, color = Gray3)
                }
                innerTextField()
            }
        )
        if (isError) {
            Text(
                text = errorMessage,
                style = SoptTypography.m14,
                color = Red,
                modifier = Modifier.padding(start = 8.dp, top = 6.dp)
            )
        }
    }
}
@Composable
fun SoptButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(if (enabled) Black else Gray1)
            .clickable(enabled = enabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = SoptTypography.sb14, color = if (enabled) White else Gray3)
    }
}
@Composable
fun RegisterScreen(
    onRegisterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }

    val isEmailError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordError = password.isNotEmpty() && password.length < 6
    val isPasswordCheckError = passwordCheck.isNotEmpty() && passwordCheck != password

    val isButtonEnabled =
        email.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty() && !isEmailError && !isPasswordError && !isPasswordCheckError

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "이메일로 회원가입", style = SoptTypography.b28, color = Black)
        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "이름",
            value = name,
            onValueChange = { name = it },
            placeholder = "홍길동"
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "이메일 주소", value = email, onValueChange = { email = it },
            placeholder = "abc@email.com",
            isError = isEmailError, errorMessage = "올바른 이메일을 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호", value = password, onValueChange = { password = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordError, errorMessage = "비밀번호는 6자 이상 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호 확인", value = passwordCheck, onValueChange = { passwordCheck = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordCheckError, errorMessage = "비밀번호와 동일하게 입력해주세요."
        )
        Spacer(modifier = Modifier.height(40.dp))
        SoptButton(
            text = "회원가입",
            enabled = isButtonEnabled,
            onClick = { onRegisterClick(email, password) })
    }
}
@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () ->Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isEmailError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordError = password.isNotEmpty() && password.length < 6
    val isButtonEnabled =
        email.isNotEmpty() && password.isNotEmpty() && !isEmailError && !isPasswordError

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "이메일로 로그인하기", style = SoptTypography.b28, color = Black)
        Spacer(modifier = Modifier.height(32.dp))

        SoptTextField(
            label = "이메일 주소", value = email, onValueChange = { email = it },
            "abc@email.com",
            isError = isEmailError, errorMessage = "올바른 이메일을 입력해주세요."
        )
        Spacer(modifier = Modifier.height(20.dp))
        SoptTextField(
            label = "비밀번호", value = password, onValueChange = { password = it },
            placeholder = "6자 이상의 비밀번호", isPassword = true,
            isError = isPasswordError, errorMessage = "비밀번호는 6자 이상 입니다."
        )
        Spacer(modifier = Modifier.height(40.dp))
        SoptButton(
            text = "로그인",
            enabled = isButtonEnabled,
            onClick = { onLoginClick(email, password) })
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "아직 계정이 없으신가요?", style = SoptTypography.m14, color = Gray3)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "회원가입하기",
                style = SoptTypography.sb14,
                color = Black,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoneTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = White) { innerPadding ->
                  RegisterScreen(
                      modifier = Modifier.padding(innerPadding),
                      onRegisterClick = {email, password ->
                          val resultIntent = Intent()
                          resultIntent.putExtra("email", email)
                          resultIntent.putExtra("password", password)
                          setResult(RESULT_OK, resultIntent)
                          finish()
                      }
                  )
                }
            }
        }
    }
}

class LoginActivity : ComponentActivity() {
    private var registeredEmail = ""
    private var registeredPassword = ""
    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            registeredEmail = result.data?.getStringExtra("email") ?: ""
            registeredPassword = result.data?.getStringExtra("password") ?: ""
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoneTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = White
                ) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onLoginClick = { email, password ->
                            if (email == registeredEmail && password == registeredPassword) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "이메일 또는 비밀번호가 올바르지 않아요.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        onRegisterClick = {
                            registerLauncher.launch(
                                Intent(
                                    this@LoginActivity, RegisterActivity::class.java
                                )
                            )
                        }
                    )
                }
            }
        }
    }

}