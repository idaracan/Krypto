package adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.unersame.krypto.R
import data.Coin
import kotlinx.android.synthetic.main.list_item.view.*

class CoinAdapter(private val coins: Map<Int,Coin>, private val context: Context)
    : RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent,false))
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.symbol.text = coins.get(position)?.symbol
        holder.name.text = coins.get (position)?.name
        holder.name.text = coins.get (position)?.price.toString()

    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val name =  view.coinName
    val symbol = view.coinSymbol
    val price = view.price
}