package pham.hien.thi_13_ph18604.ViewModel

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import pham.hien.thi_13_ph18604.Api.RetrofitInterface
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.Utils.Constant
import pham.hien.thi_13_ph18604.Utils.RetrofitHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitViewModel : ViewModel() {
    val mList: MutableLiveData<ArrayList<Model>> = MutableLiveData()

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.URL.BASE)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder()
                    .setLenient()
                    .create()))
            .build().create(RetrofitInterface::class.java)
    }

    fun getList() {
        viewModelScope.launch {
            try {
                mList.value = api.get()
                Log.d(TAG, mList.toString())
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun add(mModel: Model, addDone: () -> Unit) {
        viewModelScope.launch {
            try {
                api.add(mModel)
                getList()
                addDone()
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun update( mModel: Model) {
        viewModelScope.launch {
            try {
                mModel.id?.let { api.update(it,mModel) }
                getList()
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun delete( mModel: Model) {
        viewModelScope.launch {
            try {
                mModel.id?.let { api.delete(it) }
                getList()
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }


    companion object {
        const val TAG = "MainViewModel"
        const val URL_API = "https://60af714e5b8c300017decbb5.mockapi.io/"
        const val TB_NAME = "moto_tb"
    }
}