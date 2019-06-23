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
import com.manuelbacallado.gymprogress.adapters.RoutineAdapter
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Routine

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import java.time.LocalDate
import java.time.Month
import kotlin.collections.ArrayList

class RoutineActivity : AppCompatActivity() {

    private val list: ArrayList<Routine> by lazy { getRoutines() }

    private lateinit var routineRecycler: RecyclerView
    private lateinit var routineAdapter: RoutineAdapter
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
        routineRecycler = recyclerView
        routineRecycler.setHasFixedSize(true)
        routineRecycler.itemAnimator = DefaultItemAnimator()
        routineRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        routineRecycler.layoutManager = layoutManager
        routineAdapter = (RoutineAdapter(list, object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Clic", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, TrainingDayActivity::class.java))
            }

            override fun onLongClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Long Clic", Toast.LENGTH_LONG).show()
            }
        }))
        routineRecycler.adapter = routineAdapter
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

    private fun getRoutines(): ArrayList<Routine> {
        return object: ArrayList<Routine>() {
            init {
                add(Routine(1,"Routine1", LocalDate.of(2019, Month.JUNE, 20), LocalDate.of(2019, Month.JUNE, 28),8, "TRX"))
                add(Routine(2,"Routine2", LocalDate.of(2019, Month.JUNE, 20), LocalDate.of(2019, Month.JUNE, 20), 5, "Crossfit"))
                add(Routine(3,"Routine3", LocalDate.of(2019, Month.JUNE, 20), LocalDate.of(2019, Month.JUNE, 20), 2, "Normal"))
                add(Routine(4,"Routine4", LocalDate.of(2019, Month.JULY, 20), LocalDate.of(2019, Month.AUGUST, 28),8, "TRX"))
                add(Routine(5,"Routine5", LocalDate.of(2019, Month.SEPTEMBER, 20), LocalDate.of(2019, Month.SEPTEMBER, 28),8, "TRX"))
            }
        }
    }
}
