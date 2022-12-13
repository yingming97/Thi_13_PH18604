package pham.hien.thi_13_ph18604.ViewModel

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import org.json.JSONObject
import pham.hien.thi_13_ph18604.Model.Model


class VolleyViewModel : ViewModel() {

    val mList: MutableLiveData<ArrayList<Model>> = MutableLiveData()
    private val db = Firebase.firestore


    fun getList(activity: Activity) {
        viewModelScope.launch {
            val queue = Volley.newRequestQueue(activity)
            val stringRequest = StringRequest(
                Request.Method.GET, URL_API,
                { response ->
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(response, Array<Model>::class.java)
                    activity.runOnUiThread {
                        mList.value = result.toCollection(ArrayList())
                    }
                    Log.e(TAG, response)
                },
                {
                    Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                    Log.e(TAG, it.message.toString())
                })

            queue.add(stringRequest)
        }
    }

    fun add(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            val queue = Volley.newRequestQueue(activity)
            val jsonObject = JSONObject()
//            jsonObject.put("createdAt", mModel.createdAt)
//            jsonObject.put("name", mModel.name)
//            jsonObject.put("image", mModel.image)
//            jsonObject.put("price", mModel.price)
//            jsonObject.put("color", mModel.color)

            val stringReq = JsonObjectRequest(
                Request.Method.POST, URL_API, jsonObject,
                { response ->
                    val strResp = response.toString()
                    getList(activity)
                    Log.d(TAG, strResp)
                },
                { error ->
                    Log.d(TAG, "error => $error")
                }
                )
            queue.add(stringReq)
        }
    }

//    fun update(activity: Activity, mModel: Model) {
//        Log.e(TAG, URL_API+"/${mModel.id}")
//        Toast.makeText(activity, "URL_API/${mModel.id}", Toast.LENGTH_LONG).show()
//        viewModelScope.launch {
//            val queue = Volley.newRequestQueue(activity)
//            val jsonObject = JSONObject()
//            jsonObject.put("createdAt", mModel.createdAt)
//            jsonObject.put("name", mModel.name)
//            jsonObject.put("image", mModel.image)
//            jsonObject.put("price", mModel.price)
//            jsonObject.put("color", mModel.color)
//
//            val stringReq = JsonObjectRequest(
//                Request.Method.PUT, URL_API+"/${mModel.id}", jsonObject,
//                { response ->
//                    val strResp = response.toString()
//                    getList(activity)
//                    Log.d(TAG, strResp)
//                },
//                { error ->
//                    Log.d(TAG, "error => $error")
//                }
//            )
//            queue.add(stringReq)
//        }
//    }

//    fun delete(activity: Activity, mModel: Model) {
//        viewModelScope.launch {
//            val queue = Volley.newRequestQueue(activity)
//            val jsonObject = JSONObject()
//            jsonObject.put("createdAt", mModel.createdAt)
//            jsonObject.put("name", mModel.name)
//            jsonObject.put("image", mModel.image)
//            jsonObject.put("price", mModel.price)
//            jsonObject.put("color", mModel.color)
//
//            val stringReq = JsonObjectRequest(
//                Request.Method.DELETE, URL_API+"/${mModel.id}", jsonObject,
//                { response ->
//                    val strResp = response.toString()
//                    getList(activity)
//                    Log.d(TAG, strResp)
//                },
//                { error ->
//                    Log.d(TAG, "error => $error")
//                }
//            )
//            queue.add(stringReq)
//        }
//    }

//    fun backup(activity: Activity) {
//        val listBackup = mList.value
//        for (model in listBackup!!) {
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
        const val URL_API = "https://60af714e5b8c300017decbb5.mockapi.io/moto"
        const val TB_NAME = "moto_tb"
    }
}