package com.android.unersame.krypto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import connectivity.APIController
import connectivity.ServiceVolley
import kotlinx.android.synthetic.main.activity_main.*
import util.Constants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getLatests(view: View) {
        val serviceVolley = ServiceVolley()
        val apiController = APIController(serviceVolley)
        apiController.get(Constants.urlLatests) {  response ->
            textView.text = response.toString()
        }
    }
}
