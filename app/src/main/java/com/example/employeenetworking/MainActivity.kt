package com.example.employeenetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.employeenetworking.model.Poster
import com.example.employeenetworking.utils.Logger
import com.example.employeenetworking.utils.Logger.Companion.d
import com.example.employeenetworking.volley.VolleyHandler
import com.example.employeenetworking.volley.VolleyHttp


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }
    fun initView(){
        var text = findViewById<TextView>(R.id.tv_text)
//        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(),
//        object : VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("SHerzod",response!!)
//                Logger.d("@@@",response!!)
//                text.text = response
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("SHerzod",error!!)
//                Logger.d("@@@",error!!)
//            }
//        })
        val poster = Poster(1,"Sherzod",1200,21)
        VolleyHttp.post(VolleyHttp.API_CREATE_POST,VolleyHttp.paramsCreate(poster),object :
        VolleyHandler{
            override fun onSuccess(response: String?) {
                Logger.d("@@@",response!!)
                text.text = response
            }

            override fun onError(error: String?) {
                Logger.d("@@@",error!!)
            }
        })

//        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id,VolleyHttp.paramsCreate(poster),object :
//            VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@",response!!)
//                txt.text = response
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@",error!!)
//            }
//        })
//        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id,object : VolleyHandler{
//            override fun onSuccess(response: String?) {
//                Logger.d("@@@",response!!)
//                text.text = response
//            }
//
//            override fun onError(error: String?) {
//                Logger.d("@@@",error!!)
//            }
//        })

    }
    }
