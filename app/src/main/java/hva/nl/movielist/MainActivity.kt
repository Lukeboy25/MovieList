package hva.nl.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val DETAIL_MOVIE_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val movieItems = arrayListOf<MoviesList.Movie>()
    private val movieAdapter = MovieAdapter(movieItems) { movieItem -> onSubmitButton(movieItem) }
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvMovies.layoutManager = GridLayoutManager(this@MainActivity, 2, RecyclerView.VERTICAL, false)
        rvMovies.adapter = movieAdapter

        btnSubmit.setOnClickListener {
            val year = tiYear.text.toString()
            viewModel.getMovieItems(year)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.movies.observe(this, Observer { movies ->
            movieItems.clear()
            movies.results?.forEach { movie -> movieItems.add(movie) }
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun onSubmitButton(movie: MoviesList.Movie) {
        startDetailActivity(movie)
    }

    private fun startDetailActivity(movie : MoviesList.Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("backdropPath", movie.getBackDropImage())
        intent.putExtra("posterPath", movie.getPosterImage())
        intent.putExtra("releaseDate", movie.getReleaseDate())
        intent.putExtra("title", movie.title)
        intent.putExtra("rating", movie.vote_average)
        intent.putExtra("overview", movie.overview)
        startActivityForResult(intent, DETAIL_MOVIE_REQUEST_CODE)
    }
}
