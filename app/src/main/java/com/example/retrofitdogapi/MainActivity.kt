package com.example.retrofitdogapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.retrofitdogapi.databinding.ActivityMainBinding
import com.example.retrofitdogapi.networkUtils.BreedDetails
import com.example.retrofitdogapi.networkUtils.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var breedList = listOf<BreedDetails>()
    private lateinit var adapter: DogBreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DogBreedAdapter(this, breedList)

        binding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rv.adapter = adapter

        binding.btn.setOnClickListener {
            binding.pb.visibility = View.VISIBLE
            // fetch the details using retrofit call
            api.fetchDogBreeds().enqueue(object : Callback<List<BreedDetails>?> {
                override fun onResponse(
                    call: Call<List<BreedDetails>?>,
                    response: Response<List<BreedDetails>?>
                ) {
                    // when there is any success response from the api, this function is get called.
                    // responses are : 201, 202, 200, etc
                    if (response.isSuccessful) {
                        binding.pb.visibility = View.GONE
                        binding.rv.visibility = View.VISIBLE
                        binding.btn.visibility = View.GONE

                        val data = response.body() ?: listOf()
                        adapter.refreshList(data)
                    }
                }

                override fun onFailure(call: Call<List<BreedDetails>?>, t: Throwable) {
                    // when there is any kind of failure response, this function is called.
                    // responses here are : 404, 400, 500, etc
                    binding.pb.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Failed to load ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}