package pham.hien.thi_13_ph18604.Api

import pham.hien.thi_13_ph18604.Model.Model
import retrofit2.http.*

interface RetrofitInterface {

    @GET("moto")
    suspend fun get(): ArrayList<Model>

    @POST("moto")
    suspend fun add(
        @Body model: Model
    ): Model

    @PUT("moto/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body model: Model
    ): Model

    @DELETE("moto/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Model
}