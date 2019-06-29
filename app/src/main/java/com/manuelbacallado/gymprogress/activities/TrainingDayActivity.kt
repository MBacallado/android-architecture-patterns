package com.manuelbacallado.gymprogress.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.adapters.TrainingDaysAdapter
import com.manuelbacallado.gymprogress.db.dao.TrainingDaysDAO
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.utils.Constants

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlin.collections.ArrayList

class TrainingDayActivity : AppCompatActivity() {

    private val list: ArrayList<TrainingDay> by lazy { refreshData() }

    private lateinit var trainingDayRecycler: RecyclerView
    private lateinit var trainingDayAdapter: TrainingDaysAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    private var longClickItemPosition: Int = 0
    private var routineId : Int = 0
    private lateinit var db : TrainingDaysDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        db = TrainingDaysDAO(this)
        if (intent.extras != null) {
            routineId = intent.extras.getInt(Constants.ROUTINE_ID)
        }
        setRecycler()
        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, InsertTrainingDayActivity::class.java)
            intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
            intent.putExtra(Constants.ROUTINE_ID, routineId)
            startActivity(intent)
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
                Toast.makeText(applicationContext, "Mostrando training id: ${list.get(position).trainingDayId}", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, ExerciseActivity::class.java)
                intent.putExtra(Constants.TRAINING_ID, list.get(position).trainingDayId)
                startActivity(intent)
            }

            override fun onLongClick(concrete: Any, position: Int) {
                longClickItemPosition = position
                openContextMenu(trainingDayRecycler)
            }
        }))
        trainingDayRecycler.adapter = trainingDayAdapter
        registerForContextMenu(trainingDayRecycler)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.options_long_clic, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.edit ->{
                val intent = Intent(applicationContext, InsertTrainingDayActivity::class.java)
                intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, true)
                intent.putExtra(Constants.TRAININGDAY, list.get(longClickItemPosition))
                intent.putExtra(Constants.ROUTINE_ID, routineId)
                startActivity(intent)
                return true
            }
            R.id.delete ->{
                db.deleteElement(list.get(longClickItemPosition))
                list.remove(list.get(longClickItemPosition))
                trainingDayAdapter.notifyDataSetChanged()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun refreshData(): ArrayList<TrainingDay> {
        return db.getAllElements(routineId) as ArrayList<TrainingDay>
    }
}
