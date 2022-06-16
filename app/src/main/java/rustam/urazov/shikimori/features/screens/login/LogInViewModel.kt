package rustam.urazov.shikimori.features.screens.login

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rustam.urazov.shikimori.core.platform.BaseViewModel
import rustam.urazov.shikimori.core.result.Result
import rustam.urazov.shikimori.features.models.*
import javax.inject.Inject

@HiltViewModel
class LogInViewModel
@Inject constructor(
    private val authorize: Authorize,
    private val saveTokens: SaveTokens
) : BaseViewModel() {

    sealed class State {
        object Waiting : State()
        data class TokenView(val accessToken: String, val refreshToken: String) : State()
        object Saved : State()
    }

    sealed class Action {
        data class Authorize(val authorizationCodeView: AuthorizationCodeView) : Action()
    }

    private val mutableToken = MutableStateFlow<State>(State.Waiting)
    val token: StateFlow<State> = mutableToken.asStateFlow()

    init {
        viewModelScope.launch {
            loadState()
        }
    }

    fun sendAction(action: Action) = viewModelScope.launch {
        when (action) {
            is Action.Authorize -> authorize(action.authorizationCodeView)
        }
    }

    private fun loadState() = viewModelScope.launch {
        mutableToken.value = State.Waiting
    }

    private fun authorize(authorizationCodeView: AuthorizationCodeView) =
        authorize(authorizationCodeView.mapToAuthorizationData(), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleReceivedToken
            )
        }

    fun saveTokens(tokenView: State.TokenView) =
        saveTokens(Token(tokenView.accessToken, tokenView.refreshToken), viewModelScope) {
            it.fold(
                ::handleFailure,
                ::handleSavedToken
            )
        }

    private fun handleReceivedToken(token: Token) {
        mutableToken.value = State.TokenView(token.accessToken, token.refreshToken)
        saveTokens(mutableToken.value as State.TokenView)
    }

    private fun handleSavedToken(result: Result<Nothing>) {
        when (result) {
            is Result.Saved -> {
                mutableToken.value = State.Saved
            }
            else -> {}
        }
    }
}