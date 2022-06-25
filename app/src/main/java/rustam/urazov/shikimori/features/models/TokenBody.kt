package rustam.urazov.shikimori.features.models

import com.google.gson.annotations.SerializedName

data class TokenBody(
    @SerializedName("grant_type") val grantType: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("code") val code: String,
    @SerializedName("redirect_uri") val redirectUri: String
)