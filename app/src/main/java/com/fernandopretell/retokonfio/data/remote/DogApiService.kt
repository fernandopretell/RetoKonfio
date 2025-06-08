package com.fernandopretell.retokonfio.data.remote

import com.fernandopretell.retokonfio.data.model.DogDto
import retrofit2.http.GET

interface DogApiService {

    @GET("1151549092634943488")
    suspend fun getDogs(): List<DogDto>
}
