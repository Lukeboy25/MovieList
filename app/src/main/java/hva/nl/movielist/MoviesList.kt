package hva.nl.movielist

class MoviesList {
    var results: List<Movie>? = null

    class Movie {
        var vote_average: Double = 0.toDouble()
        var title: String? = null
        var poster_path: String? = null
        var backdrop_path: String? = null
        var overview: String? = null
        var release_date: String? = null

        fun getReleaseDate() = "Release : $release_date"
        fun getPosterImage() = "https://image.tmdb.org/t/p/w500$poster_path"
        fun getBackDropImage() = "https://image.tmdb.org/t/p/original$backdrop_path"
    }

}
