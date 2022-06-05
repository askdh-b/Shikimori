package rustam.urazov.shikimori.screen.login

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rustam.urazov.shikimori.R
import rustam.urazov.shikimori.ui.theme.*


@Composable
fun LogIn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 144.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 18.dp),
            text = "Shikimori App",
            color = BlueStart,
            fontSize = 32.sp
        )
        Link()
        AuthorizationCode()
        LogInButton(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .size(48.dp),
            text = "Войти",
            gradient = Brush.horizontalGradient(listOf(BlueStart, BlueFinish)),
            onCLick = {})
    }
}

@Composable
fun Link() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 18.dp, end = 18.dp, bottom = 6.dp)
            .border(
                width = 1.5.dp,
                brush = Brush.horizontalGradient(colors = listOf(BlueStart, BlueFinish)),
                shape = RoundedCornerShape(size = 4.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 18.dp), text = URL_SHORT,
            color = DarkGray,
            maxLines = 1
        )
        Redirect()
    }
}

@Composable
fun Redirect() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp, horizontal = 9.dp), horizontalArrangement = Arrangement.End
    ) {
        Column(horizontalAlignment = Alignment.End) {
            IconButton(modifier = Modifier.size(36.dp), onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_redirect),
                    contentDescription = null,
                    tint = BlueFinish
                )
            }
        }
    }
}

@Composable
fun AuthorizationCode() {
    var authorizationCode by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 6.dp),
        value = authorizationCode,
        onValueChange = { authorizationCode = it },
        label = { Text(text = AUTHORIZATION_CODE) },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueFinish,
            focusedLabelColor = BlueFinish,
            unfocusedBorderColor = DarkGray,
            unfocusedLabelColor = DarkGray,
            cursorColor = BlueFinish
        )
    )
}

@Composable
fun LogInButton(
    modifier: Modifier = Modifier,
    text: String,
    gradient: Brush,
    onCLick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 24.dp)
                .then(modifier)
                .align(Alignment.Bottom),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(),
            onClick = onCLick
        ) {
            Box(
                modifier = Modifier
                    .then(modifier)
                    .background(gradient)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = text,
                    fontSize = 24.sp,
                    color = White
                )
            }
        }
    }
}

private const val AUTHORIZATION_CODE = "Код авторизации"
private const val URL_SHORT = "https://shikimori.one/oauth/"
private const val URL =
    "https://shikimori.one/oauth/authorize?client_id=5ST0AkJ5LcBgIAXVkxKrtL_Wk33tgtPlyyYv-A68xNs&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&response_type=code&scope=user_rates+comments+topics"