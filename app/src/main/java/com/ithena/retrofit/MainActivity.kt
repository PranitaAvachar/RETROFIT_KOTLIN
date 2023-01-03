package com.ithena.retrofit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


const val BASE_URL="https://fakestoreapi.com/"
class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerview_product.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_product.layoutManager=linearLayoutManager

        getMyData()
    }

    private fun getMyData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyProductClassItem>?> {
            override fun onResponse(
                call: Call<List<MyProductClassItem>?>,
                response: Response<List<MyProductClassItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()

                recyclerview_product.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<MyProductClassItem>?>, t: Throwable) {
             d("MainActivity","onFailure:"+t.message)
            }
        })
    }
}
