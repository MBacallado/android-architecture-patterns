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
import com.manuelbacallado.gymprogress.adapters.ExerciseAdapter
import com.manuelbacallado.gymprogress.db.dao.ExerciseDAO
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.utils.Constants

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() {

    private val list: ArrayList<Exercise> by lazy { refreshData() }

    private lateinit var exerciseRecycler: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    private var longClickItemPosition: Int = 0
    private var trainingId : Int = 0
    private lateinit var db : ExerciseDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        db = ExerciseDAO(this)
        if (intent.extras != null) {
            trainingId = intent.extras.getInt(Constants.TRAINING_ID)
        }
        setRecycler()
        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, InsertExerciseActivity::class.java)
            intent.putExtra(Constants.LOAD_TRAINING_BOOLEAN, false)
            intent.putExtra(Constants.TRAINING_ID, trainingId)
            startActivity(intent)
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
                longClickItemPosition = position
                openContextMenu(exerciseRecycler)
            }
        }))
        exerciseRecycler.adapter = exerciseAdapter
        registerForContextMenu(exerciseRecycler)
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
                intent.putExtra(Constants.EXERCISE, list.get(longClickItemPosition))
                intent.putExtra(Constants.TRAINING_ID, trainingId)
                startActivity(intent)
                return true
            }
            R.id.delete ->{
                db.deleteElement(list.get(longClickItemPosition))
                list.remove(list.get(longClickItemPosition))
                exerciseAdapter.notifyDataSetChanged()
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

    private fun refreshData(): ArrayList<Exercise> {
        return db.getAllElements(trainingId) as ArrayList<Exercise>
    }
}
