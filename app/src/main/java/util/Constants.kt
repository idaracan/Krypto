package util

import org.json.JSONObject

class Constants {
    companion object {

        val apiKey: JSONObject = JSONObject().put(
            "X-CMC_PRO_API_KEY","df178069-5fd5-4f7d-893e-7b558d296356")

        const val urlLatests: String = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"

    }
}