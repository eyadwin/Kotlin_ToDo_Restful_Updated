package com.example.kotlin_todo_restful_updated

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.kotlin_todo_restful_updated.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AndroidNetworking.initialize(getApplicationContext());
    }
    fun toDoClicked(view: View){
        getTodo()
    }
    fun getTodo( ){
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/todos/1")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // Text will show success if Response is success
                    Log.d("Iyad","The JSON data is \n${response.toString()}")
                    processJson(response.toString())
                }
                override fun onError(anError: ANError) {
                    // Text will show failure if Response is failure
                    Log.d("Iyad","response failed")
                }
            })
    }
    fun processJson(result:String){
        val json = JSONObject(result)
        binding.tvTodoTitle.text =   json.getString("title")
        binding.tvTodoCompleted.text =  json.getString("completed")
    }
}