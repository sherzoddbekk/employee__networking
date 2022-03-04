package com.example.employeenetworking.volley

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.employeenetworking.MyApplication
import com.example.employeenetworking.model.Poster
import com.example.employeenetworking.utils.Logger
import com.example.employeenetworking.utils.Logger.Companion.d
import org.json.JSONObject


class VolleyHttp {
   companion object{
       val TAG = VolleyHttp::class.java.simpleName
       val IS_TESTER = true
       val SERVER_DEVELOMENT = "http://dummy.restapiexample.com/api/v1/"
       val SERVER_PRODUCTION = "http://dummy.restapiexample.com/api/v1/"

       fun server(url: String): String {
           if (IS_TESTER)
               return SERVER_DEVELOMENT + url
           return SERVER_PRODUCTION
       }

       fun get(api: String, params: HashMap<String, String>, volleyHandler: VolleyHandler) {
           val stringRequest = object : StringRequest(Method.GET, server(api),
               Response.Listener { response ->
                   Logger.d(TAG, response)
                   volleyHandler.onSuccess(response)
               },
               Response.ErrorListener { error ->
                   Logger.d(TAG, error.toString())
                   volleyHandler.onError(error.toString())
               }) {
               override fun getParams(): MutableMap<String, String> {
                   return params
               }
           }
           MyApplication.instance!!.addToRequestQueue(stringRequest)
       }

       fun post(api: String, body: HashMap<String, Any>,volleyHandler: VolleyHandler){
           val  stringRequest = object : StringRequest(Method.POST, server(api),
           Response.Listener { response ->
               Logger.d(TAG, response)
               volleyHandler.onSuccess(response)
           },
           Response.ErrorListener { error ->
               Logger.d(TAG,error.toString())
               volleyHandler.onError(error.toString())
           })
           {
               override fun getBody(): ByteArray {
                   return JSONObject(body as Map<*,*>).toString().toByteArray()
               }
           }
           MyApplication.instance!!.addToRequestQueue(stringRequest)
       }

       fun put(api: String, body: HashMap<String, Any>, volleyHandler: VolleyHandler){
           val stringRequest = object : StringRequest(Method.PUT, server(api),
           Response.Listener { response ->
               Logger.d(TAG, response)
               volleyHandler.onSuccess(response)
           },
               Response.ErrorListener { error ->
                   Logger.d(TAG, error.toString())
                   volleyHandler.onError(error.toString())
               }){
               override fun getBody(): ByteArray {
                   return JSONObject(body as Map<*,*>).toString().toByteArray()
               }
           }
              MyApplication.instance!!.addToRequestQueue(stringRequest)
       }

       fun del(url: String, volleyHandler: VolleyHandler) {
           val stringRequest = object : StringRequest(Method.DELETE, server(url),
               Response.Listener { response ->
                   Logger.d(TAG, response)
                   volleyHandler.onSuccess(response)
               },
               Response.ErrorListener { error ->
                   Logger.d(TAG, error.toString())
                   volleyHandler.onError(error.toString())
               }) {
           }
           MyApplication.instance!!.addToRequestQueue(stringRequest)
       }


       var API_LIST_POST = "employees"
       var API_SINGLE_POST = "employee/" //id
       var API_CREATE_POST = "create"
       var API_UPDATE_POST = "update/" //id
       var API_DELETE_POST = "delete/" //id

       fun paramsEmpty(): HashMap<String,String>{
           return HashMap<String,String>()
       }

       fun paramsCreate(poster: Poster):HashMap<String,Any> {
           val params = HashMap<String,Any>()
           params.put("name", poster.name)
           params.put("salary", poster.salary)
           params.put("age", poster.age)
           return params
       }
       fun paramsUpdate(poster: Poster):HashMap<String,Any>{
           val params = HashMap<String,Any>()
           params.put("id",poster.id)
           params.put("name", poster.name)
           params.put("salary", poster.salary)
           params.put("age", poster.age)
           return params
       }
   }
}