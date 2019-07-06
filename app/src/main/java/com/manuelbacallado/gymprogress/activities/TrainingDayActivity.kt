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
import com.manuelbacallado.gymprogress.adapters.TrainingDaysAdapter
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.presenter.TrainingPresenter

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*

class TrainingDayActivity : AppCompatActivity() {

    private lateinit var trainingDayRecycler: RecyclerView
    private lateinit var trainingDayAdapter: TrainingDaysAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }

    private var longClickItemPosition: Int = 0

    private val trainingPresenter = TrainingPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        trainingPresenter.initDatabase()
        trainingPresenter.loadIntentData()
        setRecycler()
        fab.setOnClickListener { view ->
            trainingPresenter.addItem()
        }
    }

    private fun setRecycler() {
        trainingDayRecycler = recyclerView
        trainingDayRecycler.setHasFixedSize(true)
        trainingDayRecycler.itemAnimator = DefaultItemAnimator()
        trainingDayRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        trainingDayRecycler.layoutManager = layoutManager
        trainingDayAdapter = (TrainingDaysAdapter(trainingPresenter.getItemList(), object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                trainingPresenter.clickItem(position)
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
                trainingPresenter.editItem(longClickItemPosition)
                return true
            }
            R.id.delete ->{
                trainingPresenter.deleteItem(longClickItemPosition)
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
}
