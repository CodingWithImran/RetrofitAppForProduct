package com.example.retrofitappforproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitappforproduct.Adapters.MyAdapters
import com.example.retrofitappforproduct.Modals.MyData
import com.example.retrofitappforproduct.Modals.Product
import com.example.retrofitappforproduct.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        // Below code work with Callback function
                    // val AllListData = retrofitBuilder.getProductData()
                   // for auto used ctrl+shift+space
           /*
                    AllListData.enqueue(object : Callback<MyData?> {
                        override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                            val responseBody = response.body()
                            val productList : List<Product> = responseBody?.products!!
                            // add Data in a string Builder
            //                val collectionDataSB = StringBuilder()
            //                for(myData in productList){
            //                    collectionDataSB.append(myData.title + " ")
            //                }

                            // Add Data in RecyclerView
                            binding.recProduct.adapter = MyAdapters(this@MainActivity, productList)
                            binding.recProduct.layoutManager = LinearLayoutManager(this@MainActivity)

                        }
                        override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                            Log.d("Main Activity", "error: " + p1.message)
                        }
                    })
            */

    // Below code with coroutine
    GlobalScope.launch (Dispatchers.IO){
        try{
            var myListData = retrofitBuilder.getProductData()
            if(myListData.isSuccessful){
                val  productList = myListData.body()?.products!!
                productList.let {
                    withContext(Dispatchers.Main){
                        binding.recProduct.adapter = MyAdapters(this@MainActivity, productList)
                        binding.recProduct.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }else{
                Toast.makeText(this@MainActivity, "Error is ", Toast.LENGTH_SHORT).show()
            }
        }catch (exception : Exception){
            Log.d("Tag", "Exception")
        }

    }


    }
}