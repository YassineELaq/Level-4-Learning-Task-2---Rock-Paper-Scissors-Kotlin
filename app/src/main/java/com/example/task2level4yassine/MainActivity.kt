package com.example.task2level4yassine

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.task2level4yassine.Database.ResultRepository

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import java.util.*

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

class MainActivity : AppCompatActivity() {

    val draw = "Draw Try again"
    val youwWin = "You win!"
    val computerWins = "Computer wins!"

    lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        resultRepository = ResultRepository(this)

        initVieuw()

    }

    private fun initVieuw() {
        tvStatstics.text = getResults()
        tvWinLoseMsg.text = ""

        imagePaper.setOnClickListener { gameProces(Options.PAPER) }
        imageRock.setOnClickListener { gameProces(Options.ROCK) }
        imageScissors.setOnClickListener { gameProces(Options.SCISSOR) }
    }


    private fun startResultActivity() {
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        startActivity(intent)

    }

    private fun gameProces(playerChoice: Options) {
        var choiceCompter = RandumEnum.randomEnum()
        val result = match(playerChoice, choiceCompter)
        updateUI(result)
        saveResultInDatabase(result)
    }


    //the image will be updated when the imagebtn clicked
    private fun updateUI(result: Result) {

        ivPlayerChoice.setImageResource(ConvertImage.getDrawableFromChoice(result.playerChoice))
        ivComputerChoice.setImageResource(ConvertImage.getDrawableFromChoice(result.computerChoice))
        tvWinLoseMsg.text = result.winnar
        tvStatstics.text = getResults()
    }

    private fun getWinner(playerChoice: Options, computerChoice: Options): String {
        if (playerChoice == computerChoice) {
            return draw
        }
        if (playerChoice == Options.ROCK && computerChoice == Options.SCISSOR ||
            playerChoice == Options.PAPER && computerChoice == Options.ROCK ||
            playerChoice == Options.SCISSOR && computerChoice == Options.PAPER
        )
            return youwWin
        else return computerWins
    }

    private fun match(playerChoice: Options, computerChoice: Options): Result {
        val result = getWinner(playerChoice, computerChoice)
        return Result(
            playerChoice.toString(),
            computerChoice.toString(),
            result,
            Calendar.getInstance().time.toString()
        )

    }

    private fun saveResultInDatabase(result: Result) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                resultRepository.insertResult(result)
            }

        }
    }

    private fun getResults(): String {
        var resultString = ""
        runBlocking(Dispatchers.IO) {
            val wins = async {
                resultRepository.getNumberOfWins(youwWin).toString()
            }
            val loses = async {
                resultRepository.getNumberOfWins(computerWins).toString()
            }
            val draws = async {
                resultRepository.getNumberOfWins(draw).toString()
            }
            wins.await() + loses.await() + draws.await()
            resultString =
                "Wins : " + wins.getCompleted() + " Loses : " + loses.getCompleted() + " Draws : " + draws.getCompleted()

        }


        return resultString
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
            R.id.action_show_history -> {
                startResultActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
