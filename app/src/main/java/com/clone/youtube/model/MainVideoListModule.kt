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
    @Provides // module에서 provides를 하는 이유는 retrofit같은 외부 라이브러리 객체도 의존성 주입을 할 수 있기 때문. inject를 못함
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
