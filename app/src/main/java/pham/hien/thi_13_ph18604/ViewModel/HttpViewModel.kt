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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import pham.hien.thi_13_ph18604.Model.Model
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class HttpViewModel : ViewModel() {
    val mList: MutableLiveData<ArrayList<Model>> = MutableLiveData()
    private val db = Firebase.firestore

    private val httpRequest by lazy {
        Request.Builder()
            .url(URL_API)
            .build()
    }

    fun getList(activity: Activity) {
        viewModelScope.launch {
            val fetch = OkHttpClient()
            fetch.newCall(httpRequest).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "${e.hashCode()}|| ${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<Model>::class.java)
                    activity.runOnUiThread {
                        mList.value = result.toCollection(ArrayList())
                    }
                }
            })
        }
    }

    fun add(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val url = URL(URL_API)
                    val cnt = url.openConnection() as HttpURLConnection
                    cnt.requestMethod = "POST"
                    val postData = JSONObject()
//                    postData.put("createdAt", mModel.createdAt)
//                    postData.put("name", mModel.name)
//                    postData.put("image", mModel.image)
//                    postData.put("price", mModel.price)
//                    postData.put("color", mModel.color)
                    cnt.setRequestProperty("Content-Type", "application/json")
                    val outputStream = cnt.outputStream
                    val writer = BufferedWriter(OutputStreamWriter(outputStream))
                    writer.flush()
                    writer.append(postData.toString())
                    writer.close()
                    outputStream.close()
                    val inputStream = cnt.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val builder = StringBuilder()
                    var row: String?
                    while (reader.readLine().also { row = it } != null) {
                        builder.append(row).append("\n")
                    }
                    reader.close()
                    inputStream.close()
                    cnt.disconnect()
                }
                Toast.makeText(activity, "Thêm thành công", Toast.LENGTH_LONG).show()
                getList(activity)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, e.message.toString())
                Toast.makeText(activity, "Thêm thất bại", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun update(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
//                    val url = URL(URL_API+"/${mModel.id}")
//                    val cnt = url.openConnection() as HttpURLConnection
//                    cnt.requestMethod = "PUT"
                    val postData = JSONObject()
//                    postData.put("createdAt", mModel.createdAt)
//                    postData.put("name", mModel.name)
//                    postData.put("image", mModel.image)
//                    postData.put("price", mModel.price)
//                    postData.put("color", mModel.color)
//                    cnt.setRequestProperty("Content-Type", "application/json")
//                    val outputStream = cnt.outputStream
//                    val writer = BufferedWriter(OutputStreamWriter(outputStream))
//                    writer.flush()
//                    writer.append(postData.toString())
//                    writer.close()
//                    outputStream.close()
//                    val inputStream = cnt.inputStream
//                    val reader = BufferedReader(InputStreamReader(inputStream))
//                    val builder = StringBuilder()
//                    var row: String?
//                    while (reader.readLine().also { row = it } != null) {
//                        builder.append(row).append("\n")
//                    }
//                    reader.close()
//                    inputStream.close()
//                    cnt.disconnect()
                }
                Toast.makeText(activity, "Sửa thành công", Toast.LENGTH_LONG).show()
                getList(activity)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, e.message.toString())
                Toast.makeText(activity, "Sửa thất bại", Toast.LENGTH_LONG).show()
            }

//            val client = OkHttpClient()
//            val jsonObject = JSONObject()
//            jsonObject.put("createdAt", mModel.createdAt)
//            jsonObject.put("name", mModel.name)
//            jsonObject.put("image", mModel.image)
//            jsonObject.put("price", mModel.price)
//            jsonObject.put("color", mModel.color)
//            val body = jsonObject.toString()
//                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
//            val requests = Request.Builder()
//                .url(URL_API + "/${mModel.id}")
//                .put(body)
//                .build()
//
//            try {
//                client.newCall(requests).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        Log.e(TAG, "${e.hashCode()}|| ${e.message}")
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        activity.runOnUiThread {
//                            getList(activity)
//                            Toast.makeText(activity, "Sửa thành công", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                })
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Log.e(TAG, e.message.toString())
//            }
        }
    }

    fun delete(activity: Activity, mModel: Model) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
//                    val url = URL(URL_API+"/${mModel.id}")
//                    val cnt = url.openConnection() as HttpURLConnection
//                    cnt.requestMethod = "DELETE"
//                    cnt.setRequestProperty("Content-Type", "application/json")
//                    val outputStream = cnt.outputStream
//                    val writer = BufferedWriter(OutputStreamWriter(outputStream))
//                    writer.flush()
//                    writer.close()
//                    outputStream.close()
//                    val inputStream = cnt.inputStream
//                    val reader = BufferedReader(InputStreamReader(inputStream))
//                    val builder = StringBuilder()
//                    var row: String?
//                    while (reader.readLine().also { row = it } != null) {
//                        builder.append(row).append("\n")
//                    }
//                    reader.close()
//                    inputStream.close()
//                    cnt.disconnect()
                }
                Toast.makeText(activity, "Xóa thành công", Toast.LENGTH_LONG).show()
                getList(activity)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, e.message.toString())
                Toast.makeText(activity, "Xóa thất bại", Toast.LENGTH_LONG).show()
            }

//            val client = OkHttpClient()
//            val requests = Request.Builder()
//                .url(URL_API + "/${mModel.id}")
//                .delete()
//                .build()
//
//            try {
//                client.newCall(requests).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        Log.e(TAG, "${e.hashCode()}|| ${e.message}")
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        activity.runOnUiThread {
//                            getList(activity)
//                            Toast.makeText(activity, "Xóa thành công", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                })
//
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Log.e(TAG, e.message.toString())
//            }
        }
    }

//    fun backup(activity: Activity){
//        val listBackup = mList.value
//        for (model in listBackup!!){
//            addModelBackup(model)
//        }
//        Toast.makeText(activity, "Backup thành công", Toast.LENGTH_LONG).show()
//    }
//
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