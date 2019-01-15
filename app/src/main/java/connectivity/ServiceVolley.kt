package connectivity

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class ServiceVolley : ServiceInterface {

    val TAG = ServiceVolley::class.java.simpleName

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjReq = object : JsonObjectRequest(Method.POST, path, params,
            Response.Listener<JSONObject> { response ->
                Log.d(TAG, "/post request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->
                VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                completionHandler(null)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Content-Type", "application/json")
                    return headers
                }
            }

        BackendVolley.instance?.addToRequestQueue(jsonObjReq, TAG)
    }

    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        val jsonObjectRequest = object : JsonObjectRequest(Method.GET, path, null,
            Response.Listener<JSONObject> {response ->
                Log.d(TAG, "/get request OK! Response: $response")
                completionHandler(response)
            },
            Response.ErrorListener { error ->
                VolleyLog.e(TAG, "/get request fail! Error: ${error.message}")
                completionHandler(null)
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["X-CMC_PRO_API_KEY"] = "df178069-5fd5-4f7d-893e-7b558d296356"
                    return headers
                }
            }
        BackendVolley.instance?.addToRequestQueue(jsonObjectRequest, TAG)
    }
}
