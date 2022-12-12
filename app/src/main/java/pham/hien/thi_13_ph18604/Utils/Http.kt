package pham.hien.thi_13_ph18604.Utils

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import pham.hien.thi_13_ph18604.Model.Model
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun getAllCategory() {
    val listUser = ArrayList<Model>()
    try {
        val url = URL(Constant.URL.BASE)
        val cnt = url.openConnection()
        val inputStream = cnt.getInputStream()
        val reader = BufferedReader(InputStreamReader(inputStream))
        val builder = StringBuilder()
        var row: String? = ""
        while (row != null) {
            row = reader.readLine()
            builder.append(row).append("\n")
        }
        reader.close()
        inputStream.close()
//                    cnt.disconnect()
        val value = builder.toString()

        val jsonArray = JSONArray(value)
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val gson = Gson()
            val obj2 = gson.fromJson(obj.toString(), Model::class.java)
            listUser.add(obj2)
        }
//                   listUser.reversed()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}

fun postNote2(name: String, note: String, postDone: () -> Unit) {
    try {
        val url = URL(Constant.URL.BASE)
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        val postData = JSONObject()
        postData.put("name", name)
        postData.put("note", note)
        conn.setRequestProperty("Content-Type", "application/json")
        val outputStream = conn.outputStream
        val writer = BufferedWriter(OutputStreamWriter(outputStream))
        writer.append(postData.toString())
        writer.flush()
        writer.close()
        outputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}