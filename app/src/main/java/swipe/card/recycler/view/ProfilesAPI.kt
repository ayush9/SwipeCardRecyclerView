package swipe.card.recycler.view

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProfilesAPI {

    @GET("?results=10")
    fun getProfiles(): Call<ResultResponse>

    companion object {
        operator fun invoke(): ProfilesAPI {
            return Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProfilesAPI::class.java)
        }
    }
}