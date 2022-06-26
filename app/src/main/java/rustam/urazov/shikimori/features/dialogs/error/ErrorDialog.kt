package rustam.urazov.shikimori.features.dialogs.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rustam.urazov.shikimori.features.MainActivityViewModel
import rustam.urazov.shikimori.ui.theme.*

@Composable
fun ErrorDialog(
    state: MainActivityViewModel.State,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit
) {
    when (state) {
        is MainActivityViewModel.State.Visible -> {
            AlertDialog(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
                onDismissRequest = onDismissRequest,
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = onClick,
                            colors = ButtonDefaults.buttonColors(backgroundColor = BlueFinish)
                        ) {
                            Text(text = OK, color = White)
                        }
                    }
                },
                title = {
                    Text(text = state.errorMessage.title, color = Black, fontSize = 20.sp)
                },
                text = {
                    Text(text = state.errorMessage.message, color = DarkGray, fontSize = 14.sp)
                }
            )
        }
        else -> {}
    }
}

private const val OK = "ОК"