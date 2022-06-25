package rustam.urazov.shikimori.features

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor() : ViewModel() {

    sealed class State {
        data class Visible(val message: String) : State()

        object Invisible : State()
        object Closed : State()
    }

    sealed class Action {
        data class ShowDialog(val message: String) : Action()

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
            is Action.ShowDialog -> { showDialog(action.message) }
            is Action.CloseDialog -> { closeDialog() }
        }
    }

    private fun showDialog(message: String) {
        mutableDialogState.value = State.Visible(message)
    }

    private fun closeDialog() {
        mutableDialogState.value = State.Closed
    }
}