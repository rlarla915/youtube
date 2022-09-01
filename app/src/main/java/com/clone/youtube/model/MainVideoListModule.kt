package com.clone.youtube.model

import android.os.Build
import android.util.Log
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainVideoListModule {

    class MyJsonDeserializer() : JsonDeserializer<LocalDateTime>{
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LocalDateTime {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // json 파일에도 createTime이 아래 pattern을 맞추고 있어야 함.
                LocalDateTime.parse(json?.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        }

    }

    val gson : Gson = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, MyJsonDeserializer()).create()
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    @Singleton
    @Provides // 여기서 provide 해주는 MainVideoListServiceInstance를 다른 곳에서 inject해서 사용함.
    fun getMainVideoListServiceInstance(retrofit: Retrofit): MainVideoListServiceInstance {
        return retrofit.create(MainVideoListServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getMainVideoListInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
