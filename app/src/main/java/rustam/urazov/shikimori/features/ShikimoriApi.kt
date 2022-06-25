package rustam.urazov.shikimori.features

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import rustam.urazov.shikimori.features.models.TokenBody
import rustam.urazov.shikimori.features.models.TokenResponse

interface ShikimoriApi {

    @POST("oauth/token")
    suspend fun getAccessToken(@Body tokenBody: TokenBody): Response<TokenResponse>
}