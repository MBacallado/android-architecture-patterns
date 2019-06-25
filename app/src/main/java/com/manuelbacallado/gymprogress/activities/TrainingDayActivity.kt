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
import com.manuelbacallado.gymprogress.adapters.TrainingDaysAdapter
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.TrainingDay

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlin.collections.ArrayList

class TrainingDayActivity : AppCompatActivity() {

    private val list: ArrayList<TrainingDay> by lazy { getTrainingDays() }

    private lateinit var trainingDayRecycler: RecyclerView
    private lateinit var trainingDayAdapter: TrainingDaysAdapter
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
        trainingDayRecycler = recyclerView
        trainingDayRecycler.setHasFixedSize(true)
        trainingDayRecycler.itemAnimator = DefaultItemAnimator()
        trainingDayRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        trainingDayRecycler.layoutManager = layoutManager
        trainingDayAdapter = (TrainingDaysAdapter(list, object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Clic", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, ExerciseActivity::class.java))
            }

            override fun onLongClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Long Clic", Toast.LENGTH_LONG).show()
            }
        }))
        trainingDayRecycler.adapter = trainingDayAdapter
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

    private fun getTrainingDays(): ArrayList<TrainingDay> {
        return object: ArrayList<TrainingDay>() {
            init {
                //add(TrainingDay(1,"Lunes", 45, "Pecho-Biceps"))
                //add(TrainingDay(2,"Martes", 70, "Full Body"))
                //add(TrainingDay(3,"Miercoles", 30, "Biceps-Triceps"))
                //add(TrainingDay(4,"Jueves", 20, "Full Body"))
                //add(TrainingDay(5,"Viernes", 50, "Hombros"))
            }
        }
    }
}
