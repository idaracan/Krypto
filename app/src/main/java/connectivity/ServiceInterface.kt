package connectivity

import android.content.Context
import org.json.JSONObject

interface ServiceInterface {
    fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit)
    fun get (path: String, context: Context ,completionHandler: (response: JSONObject?) -> Unit)
}