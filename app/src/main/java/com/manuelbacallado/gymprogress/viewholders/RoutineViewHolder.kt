package com.manuelbacallado.gymprogress.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Routine
import kotlinx.android.synthetic.main.routine_item.view.*

class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(routine: Routine, listener: RecyclerViewListeners) = with(itemView) {
        routineName.text = routine.name
        trainingDays.text = routine.trainingDays.toString()
        type.text = routine.trainingTypes
        startDate.text = routine.startDate.toString()
        finishDate.text = routine.finishDate.toString()
        //Click events
        setOnClickListener { listener.onClick(routine, adapterPosition) }
        setOnLongClickListener { listener.onLongClick(routine, adapterPosition) ;true}
    }
}