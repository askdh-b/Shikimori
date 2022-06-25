package rustam.urazov.shikimori.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rustam.urazov.shikimori.features.StorageService
import rustam.urazov.shikimori.features.StorageServiceImpl
import rustam.urazov.shikimori.features.repositories.TokenRepository
import rustam.urazov.shikimori.features.repositories.TokenRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository =
        tokenRepositoryImpl
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideShikimoriService(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(USER_AGENT, SMMOBILE)
                .build()
            chain.proceed(request)
        }
        .build()
}

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun provideSharedPreferencesService(storageServiceImpl: StorageServiceImpl): StorageService =
        storageServiceImpl
}

private const val BASE_URL = "https://shikimori.one/"
private const val USER_AGENT = "User-Agent"
private const val SMMOBILE = "SMMobile"