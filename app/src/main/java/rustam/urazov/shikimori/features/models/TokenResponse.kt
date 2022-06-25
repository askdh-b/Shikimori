package rustam.urazov.shikimori.features.models

import com.google.gson.annotations.SerializedName
import rustam.urazov.shikimori.core.extention.empty

data class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
) {

    companion object {
        val empty = TokenResponse(accessToken = String.empty(), refreshToken = String.empty())
    }
}

fun TokenResponse.mapToToken(): Token =
    Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )