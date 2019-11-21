package hva.nl.movielist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface MoviesApiService {
    // The GET method needed to retrieve the movies.
    @GET("discover/movie?api_key=6ace061c1d6868039a6632d8cddb5fa2")
    fun getDiscoverMovies(@Query("year") year : String): Call<MoviesList>
}
