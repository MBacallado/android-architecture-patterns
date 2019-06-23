package com.manuelbacallado.gymprogress.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Exercise
import kotlinx.android.synthetic.main.exercise_item.view.*

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(exercise: Exercise, listener: RecyclerViewListeners) = with(itemView) {
        exerciseName.text = exercise.exerciseName
        initialAmountWeight.text = exercise.initialWeight.toString()
        finalAmountWeight.text = exercise.finalWeight.toString()
        //Click events
        setOnClickListener { listener.onClick(exercise, adapterPosition) }
        setOnLongClickListener { listener.onLongClick(exercise, adapterPosition) ;true}
    }
}