package org.sopt.play.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.navigation.Route

class LoginViewModel (
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val registered = savedStateHandle.toRoute<Route.Login>()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<LoginSideEffect>()
    val sideEffect: SharedFlow<LoginSideEffect> = _sideEffect.asSharedFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onLoginClick() {
        val state = _uiState.value
        val isSuccess = state.email == registered.email && state.password == registered.password

        viewModelScope.launch {
            if (isSuccess) {
                _sideEffect.emit(LoginSideEffect.NavigateToHome)
            } else {
                _sideEffect.emit(LoginSideEffect.ShowLoginFailToast)
            }
        }
    }
}
