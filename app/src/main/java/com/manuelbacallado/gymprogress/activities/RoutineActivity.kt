package com.manuelbacallado.gymprogress.activities

import android.arch.lifecycle.ViewModelProvider
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
import com.manuelbacallado.gymprogress.adapters.RoutineAdapter
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.utils.Constants
import com.manuelbacallado.gymprogress.viewmodels.RoutineViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.routine_activity.*
import kotlinx.android.synthetic.main.recycler_view.*
import kotlin.collections.ArrayList

class RoutineActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var routineViewModel: RoutineViewModel

    private lateinit var routineRecycler: RecyclerView
    private lateinit var routineAdapter: RoutineAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }
    private var longClickItemPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.routine_activity)
        setSupportActionBar(toolbar)

        routineViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(RoutineViewModel::class.java)
        routineViewModel.setDBContext(applicationContext)

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, InsertRoutineActivity::class.java)
            intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, false)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        bind();
    }

    override fun onPause() {
        unBind()
        super.onPause()
    }

    private fun bind() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(routineViewModel.getRoutines()
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.single())
            .subscribe(this::setRecycler))
    }

    private fun unBind() {
        compositeDisposable.clear()
    }

    private fun setRecycler(list: ArrayList<Routine>) {
        routineRecycler = recyclerView
        routineRecycler.setHasFixedSize(true)
        routineRecycler.itemAnimator = DefaultItemAnimator()
        routineRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        routineRecycler.layoutManager = layoutManager
        routineAdapter = (RoutineAdapter(list, object: RecyclerViewListeners {
            override fun onClick(concrete: Any, position: Int) {
                Toast.makeText(applicationContext, "Mostrando routine id: ${list.get(position).routineId}", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, TrainingDayActivity::class.java)
                intent.putExtra(Constants.ROUTINE_ID, list.get(position).routineId)
                startActivity(intent)
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
                val intent = Intent(applicationContext, InsertRoutineActivity::class.java)
                intent.putExtra(Constants.LOAD_ROUTINE_BOOLEAN, true)
                intent.putExtra(Constants.ROUTINE, routineViewModel.editItem(longClickItemPosition))
                startActivity(intent)
                return true
            }
            R.id.delete ->{
                routineViewModel.deleteItem(longClickItemPosition)
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
