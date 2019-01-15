package fragments

import adapters.CoinAdapter
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.unersame.krypto.R
import connectivity.APIController
import connectivity.ServiceVolley
import data.Coin
import kotlinx.android.synthetic.main.fragment_base.*
import org.json.JSONArray
import org.json.JSONObject
import util.CoinUtils
import util.Constants

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val initParam = "fragClass"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [BaseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
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
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Excecuted when creating a new Base Fragment
         * InitParam: value to determine the content to load
         */
        @JvmStatic
        fun newInstance(fragmentType: Int) =
            BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(initParam, fragmentType)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (fragmentType) {
            R.id.navigation_home       -> Log.i("fragment", "Home Selected")
            R.id.navigation_favorites  -> Log.i("fragment", "Favorites Selected")
            R.id.navigation_highlights -> Log.i("fragment", "Highlights Selected")
            else -> Log.i("BaseFragment", "None selected")
        }
        getData(Constants.urlLatests)
    }

    fun getData(category: String) {
        val serviceVolley = ServiceVolley()
        val apiController = APIController(serviceVolley)
        apiController.get(category) { response ->
            response?.let {
                val coins: Map<Int, Coin> = CoinUtils.getAllCoins(response)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = context?.let { it1 -> CoinAdapter(coins, it1) }
            }
        }

    }

}