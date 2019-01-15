package data

data class Coin(val id: Int, val name: String, val symbol: String, val slug: String,
                val dateAdded: String, val price:Float, val change1h: Float, val change24h: Float,
                val change7d: Float, val marketCap: Float) {
    override fun toString(): String {
        return "Id: ${id} \n Name: ${name}"
    }
}
