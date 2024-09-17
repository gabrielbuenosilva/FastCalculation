package dev.briel.fastcalculation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.briel.fastcalculation.Extras.EXTRA_SETTINGS
import dev.briel.fastcalculation.Extras.EXTRA_START_GAME_NOW
import dev.briel.fastcalculation.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity(), OnPlayGame {

    private var points: Float = 0f
    private lateinit var settings: Settings

    private val activityFinishBinding : ActivityFinishBinding by lazy {
        ActivityFinishBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(activityFinishBinding.root)

        setSupportActionBar(activityFinishBinding.finishTbIn.gameTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = getString(R.string.finish)
        }

        points = intent.getFloatExtra(Extras.EXTRA_POINTS, 0f)
        settings = intent.getParcelableExtra(Extras.EXTRA_SETTINGS) ?: Settings()

        with (activityFinishBinding) {

            "%.1f".format(points).also {

                pointsTv.text = it

            }

            restartBt.setOnClickListener() {

                onPlayGame()

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.restartGameMi -> {
                onPlayGame()
                true
            }
            R.id.exitGameMi -> {
                finish()
                true
            }
            else -> {false}
        }
    }

    override fun onPlayGame() {

        val gameActivityIntent = Intent(this@FinishActivity, GameActivity::class.java)

        gameActivityIntent.putExtra(EXTRA_SETTINGS, settings)
        gameActivityIntent.putExtra(EXTRA_START_GAME_NOW, true)

        startActivity(gameActivityIntent)
        finish()

    }
}