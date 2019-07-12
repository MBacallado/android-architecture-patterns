package com.manuelbacallado.gymprogress.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.TrainingDay
import kotlinx.android.synthetic.main.training_days_item.view.*

class TrainingDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(trainingDay: TrainingDay, listener: RecyclerViewListeners) = with(itemView) {
        trainingDayName.text = trainingDay.day
        time.text = trainingDay.timeAmount.toString()
        group.text = trainingDay.group
        //Click events
        setOnClickListener { listener.onClick(trainingDay, adapterPosition) }
        setOnLongClickListener { listener.onLongClick(trainingDay, adapterPosition) ;true}
    }
}