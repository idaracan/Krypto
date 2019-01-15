package connectivity

import org.json.JSONObject

class APIController constructor(serviceInjection: ServiceInterface): ServiceInterface {

    private val service: ServiceInterface = serviceInjection

    override fun post(path: String, params: JSONObject, completionHandler: (response: JSONObject?) -> Unit) {
        service.post(path, params, completionHandler)
    }
    override fun get(path: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.get(path, completionHandler)
    }
}