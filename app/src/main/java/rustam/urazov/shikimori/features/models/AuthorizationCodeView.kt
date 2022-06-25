package rustam.urazov.shikimori.features.models

data class AuthorizationCodeView(
    val authorizationCode: String
)

fun AuthorizationCodeView.mapToAuthorizationData(): AuthorizationData = AuthorizationData(
    grantType = AUTHORIZATION_CODE_GRANT_TYPE,
    clientId = CLIENT_ID,
    clientSecret = CLIENT_SECRET,
    code = this.authorizationCode,
    redirectUri = REDIRECT_URI
)

private const val AUTHORIZATION_CODE_GRANT_TYPE = "authorization_code"
private const val CLIENT_ID = "5ST0AkJ5LcBgIAXVkxKrtL_Wk33tgtPlyyYv-A68xNs"
private const val CLIENT_SECRET = "LkZvpgiQgjvsVUlhs2nTqOOGYhBN89vaR2osBER44xE"
private const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"