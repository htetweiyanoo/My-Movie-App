package net.myanmarlinks.retrofittest

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import net.myanmarlinks.retrofittest.model.Movies
import net.myanmarlinks.retrofittest.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val api = ApiService(this)

        fab.setOnClickListener { view ->
            val language = "en-US"
            val sort_by = "popularity.desc"
            val include_adult = false
            val include_video = false
            val page = 1
            val movieCall = api.getPopularMovies(language, sort_by, include_adult, include_video, page)
            movieCall.enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("MY_API", "Error ${t.toString()}")
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    Log.d("MY_API", response.body().toString())
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
