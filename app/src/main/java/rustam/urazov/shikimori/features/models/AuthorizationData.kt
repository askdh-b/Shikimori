package rustam.urazov.shikimori.features.models

data class AuthorizationData(
    val grantType: String,
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val redirectUri: String
)

fun AuthorizationData.mapToTokenBody(): TokenBody = TokenBody(
    grantType = this.grantType,
    clientId = this.clientId,
    clientSecret = this.clientSecret,
    code = this.code,
    redirectUri = this.redirectUri
)