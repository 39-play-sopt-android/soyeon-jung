package org.sopt.play.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.play.MainActivity
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.register.RegisterActivity

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
            PlaySoptTheme {
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