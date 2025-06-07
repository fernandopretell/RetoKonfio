package com.fernandopretell.retokonfio.data.remote

import com.fernandopretell.retokonfio.domain.model.Dog
import retrofit2.http.GET

interface DogApiService {
    @GET("1151549092634943488")
    suspend fun getDogs(): List<Dog>
}
