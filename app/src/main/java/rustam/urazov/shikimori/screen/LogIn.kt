package rustam.urazov.shikimori.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rustam.urazov.shikimori.R
import rustam.urazov.shikimori.ui.theme.DarkBlue
import rustam.urazov.shikimori.ui.theme.White

@Composable
fun LogIn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 144.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(144.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "App Logo"
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Shikimori",
            color = DarkBlue,
            fontSize = 32.sp
        )
        Row(modifier = Modifier.fillMaxHeight()) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 24.dp)
                .align(Alignment.Bottom)
                .size(48.dp)
                .clip(CircleShape)
                .border(width = 1.5.dp, color = DarkBlue, shape = CircleShape),
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                onClick = {}) {
                Text(
                    text = "Войти",
                    color = DarkBlue,
                    fontSize = 24.sp
                )
            }
        }
    }
}