package com.manuelbacallado.gymprogress.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.adapters.ExerciseAdapter
import com.manuelbacallado.gymprogress.adapters.TrainingDaysAdapter
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.models.TrainingDay

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {

    private val list: ArrayList<Exercise> by lazy { getTrainingDays() }

    private lateinit var exerciseRecycler: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        setRecycler()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setRecycler() {
        exerciseRecycler = recyclerView
        exerciseRecycler.setHasFixedSize(true)
        exerciseRecycler.itemAnimator = DefaultItemAnimator()
        exerciseRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        exerciseRecycler.layoutManager = layoutManager
        exerciseAdapter = (ExerciseAdapter(list, object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Clic", Toast.LENGTH_LONG).show()
            }

            override fun onLongClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Long Clic", Toast.LENGTH_LONG).show()
            }
        }))
        exerciseRecycler.adapter = exerciseAdapter
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getTrainingDays(): ArrayList<Exercise> {
        return object: ArrayList<Exercise>() {
            init {
                add(Exercise(1,"Press banca", 10, 15))
                add(Exercise(2,"Fondos", 10, 15))
                add(Exercise(3,"Triceps", 10, 15))
                add(Exercise(4,"Biceps", 10, 15))
                add(Exercise(5,"Piernas", 10, 15))
            }
        }
    }
}
