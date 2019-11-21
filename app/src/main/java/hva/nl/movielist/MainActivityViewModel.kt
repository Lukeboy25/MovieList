package hva.nl.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository = MovieRepository()
    val error = MutableLiveData<String>()
    val movies = MutableLiveData<MoviesList>()

    fun getMovieItems(year : String) {
        moviesRepository.getDiscoverMovies(year).enqueue(object : Callback<MoviesList?> {
            override fun onResponse(
                call: Call<MoviesList?>,
                response: Response<MoviesList?>) {
                if (response.isSuccessful) {
                    movies.value = response.body()
                } else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MoviesList?>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}