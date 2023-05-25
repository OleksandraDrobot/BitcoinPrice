package com.example.lab72

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.lab72.retrofit.BitcoinAPI
import com.example.lab72.retrofit.BitcoinPrice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val description = findViewById<TextView>(R.id.tvDescription)
        val getResult = findViewById<Button>(R.id.buttonForResult)
        val price = findViewById<TextView>(R.id.tvPrice)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coindesk.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val getAPI = retrofit.create(BitcoinAPI::class.java)

        getResult.setOnClickListener {
            getAPI.getBitcoinPrice().enqueue(object : Callback<BitcoinPrice> {
                override fun onResponse(
                    call: Call<BitcoinPrice>,
                    response: Response<BitcoinPrice>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        val currentPrice = data?.bpi?.get("USD")
                        val currentData = data?.time?.updated
                        val tariff = currentPrice?.rate ?: "Не знайдено"
                        val time = currentData ?: "Не знайдено"
                        description.text = ("$" + tariff)
                        price.text = time
                    } else {
                        description.text = "Помилка"
                    }
                }
                override fun onFailure(call: Call<BitcoinPrice>, t: Throwable) {
                    description.text = "Помилка: ${t.message}"
                }
            })
        }
    }
}
