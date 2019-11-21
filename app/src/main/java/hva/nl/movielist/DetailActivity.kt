package hva.nl.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.movie_detail)

        tvRating.text = intent.extras?.get("rating").toString()
        tvReleaseDate.text = intent.extras?.get("releaseDate").toString()
        tvTitle.text = intent.extras?.get("title").toString()
        tvOverview.text = intent.extras?.get("overview").toString()
        Glide.with(this).load(intent.extras?.get("backdropPath").toString()).into(ivBackDrop)
        Glide.with(this).load(intent.extras?.get("posterPath").toString()).into(ivPoster)
    }
}
