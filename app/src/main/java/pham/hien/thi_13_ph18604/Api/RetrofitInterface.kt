package pham.hien.thi_13_ph18604.Api

import pham.hien.thi_13_ph18604.Model.Model
import retrofit2.http.*

interface RetrofitInterface {

    @GET("laptop_ph18604")
    suspend fun get(): ArrayList<Model>

    @POST("laptop_ph18604")
    suspend fun add(
        @Body model: Model
    ): Model

    @PUT("laptop_ph18604/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body model: Model
    ): Model

    @DELETE("laptop_ph18604/{id}")
    suspend fun delete(
        @Path("id") id: String,
    ): Model
}