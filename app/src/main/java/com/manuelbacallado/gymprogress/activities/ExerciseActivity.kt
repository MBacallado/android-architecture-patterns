package com.manuelbacallado.gymprogress.activities

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
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.adapters.ExerciseAdapter
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.presenter.ExercisePresenter

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*

class ExerciseActivity : AppCompatActivity() {

    private lateinit var exerciseRecycler: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    private var longClickItemPosition: Int = 0

    private val exercisePresenter = ExercisePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        exercisePresenter.initDatabase()
        exercisePresenter.loadIntentData()
        setRecycler()
        fab.setOnClickListener { view ->
            exercisePresenter.addItem()
        }
    }

    private fun setRecycler() {
        exerciseRecycler = recyclerView
        exerciseRecycler.setHasFixedSize(true)
        exerciseRecycler.itemAnimator = DefaultItemAnimator()
        exerciseRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        exerciseRecycler.layoutManager = layoutManager
        exerciseAdapter = (ExerciseAdapter(exercisePresenter.getItemList(), object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                exercisePresenter.clickItem(position)
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
                exercisePresenter.editItem(longClickItemPosition)
                return true
            }
            R.id.delete ->{
                exercisePresenter.deleteItem(longClickItemPosition)
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
}
