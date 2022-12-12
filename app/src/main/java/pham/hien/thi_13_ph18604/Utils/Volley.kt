package pham.hien.thi_13_ph18604.Utils

import android.content.Context
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception


fun getListProductVolley(context: Context) {

    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(Request.Method.GET, Constant.URL.BASE, { response ->
//        strResponse.value = "Response is: $response"
    },
        { Log.e("YingMing", "getListProduct: ") })
    queue.add(stringRequest)
}

fun postProductVolley(context: Context) {
    try {
        val requestQueue = Volley.newRequestQueue(context)
        val jsonBody = JSONObject()
        jsonBody.put("name", "Hê lô sờ ly ly")
        jsonBody.put("note", "Thực hành Volley")
        val requestBody = jsonBody.toString()

        //Cú pháp: StringRequest stringRequest = new StringRequest(METHOD, URL, respone) {request_option};
        val stringRequest: StringRequest =
            object : StringRequest(Method.POST, Constant.URL.BASE,
                Response.Listener { response ->
                    Log.i("VOLLEY  response post ",
                        response!!)
                },
                Response.ErrorListener { error -> Log.e("VOLLEY", error.toString()) }) {
                // phần gửi đi
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray? {
                    return try {
                        requestBody.toByteArray(charset("utf-8"))
                    } catch (uee: Exception) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody,
                            "utf-8")
                        null
                    }
                }

                override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
                    var responseString = ""
                    responseString = response.statusCode.toString()
                    return Response.success(responseString,
                        HttpHeaderParser.parseCacheHeaders(response))
                }
            }


        // thực hiện kết nối server và gửi dữ liệu (volley thực thi)
        requestQueue.add(stringRequest)
    } catch (e: JSONException) {
        e.printStackTrace()
    }

}