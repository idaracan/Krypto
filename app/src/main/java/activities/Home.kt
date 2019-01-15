package activities

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.unersame.krypto.R
import fragments.BaseFragment
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity(), BaseFragment.OnFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            supportFragmentManager.beginTransaction().replace(R.id.base_fragment,
                BaseFragment.newInstance(item.itemId)).addToBackStack(null).commit()
            when (item.itemId) {
                R.id.navigation_home -> {
                    Log.i("ActivityHome", "Fragment selected -> home")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    Log.i("ActivityHome", "Fragment selected -> favorites")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_highlights -> {
                    Log.i("ActivityHome", "Fragment selected -> highlights")
                    return@OnNavigationItemSelectedListener true
                }
            }
        false
        }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportFragmentManager.beginTransaction().replace(R.id.base_fragment,
            BaseFragment.newInstance(R.id.navigation_home)).addToBackStack(null).commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
