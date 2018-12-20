package com.example.kathishan.startwarmovies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kathishan.startwarmovies.R.id.btnSubmit
import com.example.kathishan.startwarmovies.R.id.rvPasses
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val adapter = FilmAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = retrofitBuilder
            .client(okHttpClient)
            .build()

        val issClient = retrofit.create(FilmService::class.java)

        rvPasses.layoutManager = LinearLayoutManager(this)
        rvPasses.adapter = adapter

        btnSubmit.setOnClickListener {
            FilmResponse.getFilmResponse(etLatitude.text.toString().toFloat(),
                etLongitude.text.toString().toFloat()).enqueue(
                object : Callback<FilmResponse> {
                    override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<Response>, response: Response<Response>) {
                        if (response.isSuccessful) {
                            adapter.setData(response.body()?.response ?: emptyList())
                        }
                    }

                }
            )
        }
    }
}