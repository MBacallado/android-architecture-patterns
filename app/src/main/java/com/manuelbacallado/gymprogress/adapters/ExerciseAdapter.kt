package com.manuelbacallado.gymprogress.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.inflate
import com.manuelbacallado.gymprogress.listener.RecyclerViewListeners
import com.manuelbacallado.gymprogress.models.Exercise
import com.manuelbacallado.gymprogress.viewholders.ExerciseViewHolder

class ExerciseAdapter(private val exerciseList: List<Exercise>, private val listener: RecyclerViewListeners) :
    RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseViewHolder(parent.inflate(R.layout.exercise_item))

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) = holder.bind(exerciseList[position], listener)

    override fun getItemCount() = exerciseList.size
}
