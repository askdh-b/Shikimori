package rustam.urazov.shikimori.features

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.shikimori.features.models.ErrorMessage
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor() : ViewModel() {

    sealed class State {
        data class Visible(val errorMessage: ErrorMessage) : State()

        object Invisible : State()
        object Closed : State()
    }

    sealed class Action {
        data class ShowDialog(val errorMessage: ErrorMessage) : Action()

        object CloseDialog : Action()
    }

    private val mutableDialogState = MutableStateFlow<State>(State.Invisible)
    val dialogState: StateFlow<State> = mutableDialogState.asStateFlow()

    init {
        setInvisibleState()
    }

    private fun setInvisibleState() {
        mutableDialogState.value = State.Invisible
    }

    fun sendAction(action: Action) {
        when (action) {
            is Action.ShowDialog -> { showDialog(action.errorMessage) }
            is Action.CloseDialog -> { closeDialog() }
        }
    }

    private fun showDialog(errorMessage: ErrorMessage) {
        mutableDialogState.value = State.Visible(errorMessage)
    }

    private fun closeDialog() {
        mutableDialogState.value = State.Closed
    }
}