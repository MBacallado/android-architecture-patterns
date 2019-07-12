package com.manuelbacallado.gymprogress.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.inflate
import com.manuelbacallado.gymprogress.interfaces.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.TrainingDay
import com.manuelbacallado.gymprogress.viewholders.TrainingDayViewHolder

class TrainingDaysAdapter(private val trainingDaysList: List<TrainingDay>, private val listener: RecyclerViewListeners) :
    RecyclerView.Adapter<TrainingDayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingDayViewHolder(parent.inflate(R.layout.training_days_item))

    override fun onBindViewHolder(holder: TrainingDayViewHolder, position: Int) = holder.bind(trainingDaysList[position], listener)

    override fun getItemCount() = trainingDaysList.size
}
