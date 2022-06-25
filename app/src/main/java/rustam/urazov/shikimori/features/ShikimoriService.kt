package rustam.urazov.shikimori.features

import retrofit2.Response
import retrofit2.Retrofit
import rustam.urazov.shikimori.features.models.TokenBody
import rustam.urazov.shikimori.features.models.TokenResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShikimoriService
@Inject constructor(retrofit: Retrofit) : ShikimoriApi {
    private val shikimoriApi by lazy { retrofit.create(ShikimoriApi::class.java) }

    override suspend fun getAccessToken(tokenBody: TokenBody): Response<TokenResponse> =
        shikimoriApi.getAccessToken(tokenBody)
}