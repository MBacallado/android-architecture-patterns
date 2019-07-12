package com.manuelbacallado.gymprogress.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.inflate
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Routine
import com.manuelbacallado.gymprogress.viewholders.RoutineViewHolder

class RoutineAdapter(private val routines: List<Routine>, private val listener: RecyclerViewListeners) :
    RecyclerView.Adapter<RoutineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RoutineViewHolder(parent.inflate(R.layout.routine_item))

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) = holder.bind(routines[position], listener)

    override fun getItemCount() = routines.size
}
