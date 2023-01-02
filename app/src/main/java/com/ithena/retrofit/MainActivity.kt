package com.ithena.retrofit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


const val BASE_URL="https://fakestoreapi.com/"
class MainActivity : AppCompatActivity() {
    lateinit var mytext: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      mytext=findViewById(R.id.txtId)
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

                val myStringBuilder = StringBuilder()
                for (myData in responseBody){

                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
                mytext.text=myStringBuilder
            }


            override fun onFailure(call: Call<List<MyProductClassItem>?>, t: Throwable) {
             Log.d("MainActivity","onFailure:"+t.message)
            }
        })
    }
}
