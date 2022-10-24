package com.clone.youtube.repository

import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalJsonServer

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GoogleCloudStorage

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    @LocalJsonServer
    fun getRetrofitInstanceLocal(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    @GoogleCloudStorage
    fun getRetrofitInstanceCloud(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://storage.googleapis.com/")
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    class MyJsonDeserializer() : JsonDeserializer<LocalDateTime> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocalDateTime {
            return LocalDateTime.parse(
                json?.asString,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            )

        }

    }

    private val gson: Gson =
        GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, MyJsonDeserializer()).create()

    class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> {
            val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter<ResponseBody, Any> {
                if (it.contentLength() == 0L) return@Converter null
                delegate.convert(it)
            }
        }

    }

}
