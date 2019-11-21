package hva.nl.movielist


class MovieRepository {
    private val moviesApi: MoviesApiService = MoviesApi.createApi()


    fun getDiscoverMovies(year : String) = moviesApi.getDiscoverMovies(year)
}
