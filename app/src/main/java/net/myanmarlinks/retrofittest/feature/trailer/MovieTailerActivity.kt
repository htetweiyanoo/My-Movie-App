package net.myanmarlinks.retrofittest.feature.trailer

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import net.myanmarlinks.retrofittest.R

class TailerActivity : YouTubeBaseActivity() {

    val movieTrailer: YouTubePlayerView by lazy {
        findViewById<YouTubePlayerView>(R.id.movie_trailer)
    }

    private val API_KEY = "AIzaSyCy9vJeUU6iRA1rxdY28QCdkuAJFzphges"

    val videoCode = "XfP31eWXli4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_trailer)
        movieTrailer.initialize(API_KEY, object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youtubePlayer: YouTubePlayer?,
                b: Boolean
            ) {
                if (!b) {
                    youtubePlayer!!.loadVideo(videoCode)
                    youtubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Youtube Player Failure", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}