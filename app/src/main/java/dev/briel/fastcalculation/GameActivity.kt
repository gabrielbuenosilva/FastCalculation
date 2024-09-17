package dev.briel.fastcalculation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.briel.fastcalculation.Extras.EXTRA_SETTINGS
import dev.briel.fastcalculation.databinding.ActivityGameBinding
import dev.briel.fastcalculation.databinding.ActivitySettingsBinding

class GameActivity : AppCompatActivity(), OnPlayGame {

    private val activityGameBinding: ActivityGameBinding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(activityGameBinding.root)

        val startGameNow = intent.getBooleanExtra(Extras.EXTRA_START_GAME_NOW, false)

        setSupportActionBar(activityGameBinding.gameTbIn.gameTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = getString(R.string.game)
        }

        settings = intent.getParcelableExtra(EXTRA_SETTINGS) ?: Settings()


        if (startGameNow) {

            supportFragmentManager.beginTransaction().replace(R.id.gameFl, GameFragment.newInstance(settings)).commit()

        } else {

            supportFragmentManager.beginTransaction().replace(R.id.gameFl, WelcomeFragment.newInstance(settings)).commit()

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

        supportFragmentManager.beginTransaction().replace(R.id.gameFl, GameFragment.newInstance(settings)).commit()

    }

}