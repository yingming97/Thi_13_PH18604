package pham.hien.thi_13_ph18604.Utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.URL.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}