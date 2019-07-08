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
import com.manuelbacallado.gymprogress.adapters.RoutineAdapter
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.presenters.RoutinePresenter
import com.manuelbacallado.gymprogress.routers.RoutineRouter

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*

class RoutineActivity : AppCompatActivity() {

    private lateinit var routineRecycler: RecyclerView
    private lateinit var routineAdapter: RoutineAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }
    private var longClickItemPosition: Int = 0

    private var routinePresenter = RoutinePresenter();
    private var routineRouter = RoutineRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        routinePresenter.init(applicationContext);
        setRecycler(routinePresenter.getItems() as ArrayList<Routine>)
        fab.setOnClickListener { view ->
            routineRouter.goToCreate()
        }
    }

    private fun setRecycler(list: ArrayList<Routine>) {
        routineRecycler = recyclerView
        routineRecycler.setHasFixedSize(true)
        routineRecycler.itemAnimator = DefaultItemAnimator()
        routineRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        routineRecycler.layoutManager = layoutManager
        routineAdapter = (RoutineAdapter(list, object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                routineRouter.goToNextSection(list.get(position).routineId)
            }

            override fun onLongClick(concrete: Any, position: Int) {
                longClickItemPosition = position
                openContextMenu(routineRecycler)
            }
        }))
        routineRecycler.adapter = routineAdapter
        registerForContextMenu(routineRecycler)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.options_long_clic, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.edit ->{
                routineRouter.goToEdit(routinePresenter.getItem(longClickItemPosition))
                return true
            }
            R.id.delete ->{
                routinePresenter.deleteItem(longClickItemPosition)
                routineAdapter.notifyDataSetChanged()
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
