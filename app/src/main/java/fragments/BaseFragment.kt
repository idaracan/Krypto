package fragments

import adapters.CoinAdapter
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.unersame.krypto.R
import connectivity.APIController
import connectivity.ServiceVolley
import data.Coin
import kotlinx.android.synthetic.main.fragment_base.*
import org.json.JSONObject
import util.CoinUtils
import util.Constants
import util.PropertiesUtil
import java.util.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val initParam = "fragClass"

class BaseFragment : Fragment() {

    private var fragmentType: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentType = it.getInt(initParam)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (fragmentType) {
            R.id.navigation_home       -> Log.i("fragment", "Home Selected")
            R.id.navigation_favorites  -> Log.i("fragment", "Favorites Selected")
            R.id.navigation_highlights -> Log.i("fragment", "Highlights Selected")
            else -> Log.i("BaseFragment", "None selected")
        }
        val urlLatests = PropertiesUtil.getProperties("public.properties", context!!).getProperty("urlLatests")
        DownloadData(requireContext(), recyclerView).execute(urlLatests)

    }

    companion object {
        class DownloadData(context: Context, recyclerView: RecyclerView):
            AsyncTask<String, Void, String >() {

            var propContext: Context by Delegates.notNull()
            var propRecyclerView: RecyclerView by Delegates.notNull()

            init {
                propContext = context
                propRecyclerView = recyclerView
            }

            override fun doInBackground(vararg url: String): String? {
                getData(url[0])
                return "Done!"
            }

            private fun getData(category: String) {
                val serviceVolley = ServiceVolley()
                val apiController = APIController(serviceVolley)
                apiController.get(category, propContext) { response ->
                    response?.let {
                        val coins: Map<Int, Coin> = CoinUtils.getAllCoins(response)
                        propRecyclerView.layoutManager = LinearLayoutManager(propContext)
                        propRecyclerView.adapter = propContext.let { it1 -> CoinAdapter(coins, it1) }
                    }
                }
            }

        }
        @JvmStatic
        fun newInstance(fragmentType: Int) =
            BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(initParam, fragmentType)
                }
            }
    }

}