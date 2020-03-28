package com.example.task2level4yassine

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task2level4yassine.Database.ResultRepository

import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.content_result.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

class ResultActivity : AppCompatActivity() {

    private var results = arrayListOf<Result>()
    private var resultsAdapter = ResultsAdapter(results)

    private lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        resultRepository = ResultRepository(this)//injecting the repo
        initViews()
        btnDelete.setOnClickListener { deleteAllResults() }

    }

    private fun initViews() {
        rvResult.adapter = resultsAdapter
        rvResult.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvResult.addItemDecoration(
            DividerItemDecoration(
                this,
            DividerItemDecoration.VERTICAL
            )
        )
        getResultsFromDatabase()
    }

    private fun getResultsFromDatabase() {
        this@ResultActivity.results.clear()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                results.addAll(resultRepository.getAllResults())
            }
        }.invokeOnCompletion {
            this@ResultActivity.resultsAdapter.notifyDataSetChanged()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_show_history -> {
                deleteAllResults()
                true
            }
            android.R.id.home -> {
                startMainActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startMainActivity() {
        // here we use the intent becaus we will exchange data between 2 activity
        val intent = Intent(this@ResultActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun deleteAllResults() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                resultRepository.deleteAllResults()
            }
            getResultsFromDatabase()
        }

    }
}