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
import pham.hien.thi_13_ph18604.Model.Model
import pham.hien.thi_13_ph18604.Utils.RetrofitHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitViewModel: ViewModel() {
    val mList: MutableLiveData<ArrayList<Model>> = MutableLiveData()
    private val db = Firebase.firestore

    private val api by lazy {
      Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder()
                .setLenient()
                .create()))
            .build().create(RetrofitHelper::class.java)
    }

    fun getList(activity: Activity) {
        viewModelScope.launch {
            try{
//                mList.value = api.get()
                Log.d(TAG, mList.toString())
                Toast.makeText(activity, "List Update", Toast.LENGTH_LONG).show()
            }catch (e: Exception){
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun add(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
//                api.add(mModel)
                getList(activity)
            }catch (e: Exception){
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun update(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
//                api.update(mModel.id ,mModel)
                getList(activity)
            }catch (e: Exception){
                Log.e(TAG, e.message.toString())
            }
        }
    }

    fun delete(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
//                api.delete(mModel.id)
                getList(activity)
            }catch (e: Exception){
                Log.e(TAG, e.message.toString())
            }
        }
    }

//    fun backup(activity: Activity){
//        val listBackup = mList.value
//        for (model in listBackup!!){
//            addModelBackup(model)
//        }
//        Toast.makeText(activity, "Backup thành công", Toast.LENGTH_LONG).show()
//    }

//    private fun addModelBackup(mModel: Model) {
//        db.collection(TB_NAME)
//            .document(mModel.id)
//            .set(mModel)
//            .addOnSuccessListener {
//                Log.d(TAG, "Success at ${mModel.id}")
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Error: ${it.message}")
//            }
//    }

    companion object {
        const val TAG = "MainViewModel"
        const val URL_API = "https://60af714e5b8c300017decbb5.mockapi.io/"
        const val TB_NAME = "moto_tb"
    }
}