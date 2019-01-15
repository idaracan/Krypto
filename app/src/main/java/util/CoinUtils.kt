package util

import data.Coin
import org.json.JSONArray
import org.json.JSONObject

class CoinUtils {

    companion object {
        fun jsonParser(data: JSONArray, index: Int): Coin{
            val coinData: JSONObject    = data.getJSONObject(index)
            val quote: JSONObject       = coinData.getJSONObject("quote").getJSONObject("USD")
            val coin = Coin(id = coinData.getInt("id"), name = coinData.getString("name"),
                symbol = coinData.getString("symbol"), slug = coinData.getString("slug"),
                dateAdded   = coinData.getString("date_added"),
                price       = quote.get("price").toString().toFloat(),
                change1h    = quote.getString("percent_change_1h").toFloat(),
                change24h   = quote.getString("percent_change_24h").toFloat(),
                change7d    = quote.getString("percent_change_7d").toFloat(),
                marketCap   = quote.getString("market_cap").toFloat())
            return coin
        }
        fun getAllCoins(jsonObject: JSONObject): Map<Int, Coin> {
            val data: JSONArray = jsonObject.getJSONArray("data")
            var coins: MutableMap<Int, Coin> = mutableMapOf()
            for (i in 0 until data.length()){
                val coin = jsonParser(data, i)
                coins.put(i,coin)
            }
            return coins
        }
        fun getAllCoinsList(jsonObject: JSONObject): List<Coin> {
            val data: JSONArray = jsonObject.getJSONArray("data")
            val coins: MutableList<Coin> = mutableListOf()
            for (i in 0 until data.length()){
                val coin = jsonParser(data, i)
                coins.add(coin)
            }
            return coins
        }
    }

}