package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenters.InsertExercisePresenter
import kotlinx.android.synthetic.main.insert_exercise_item.*

class InsertExerciseActivity : AppCompatActivity() {

    private var insertExercisePresenter = InsertExercisePresenter(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_exercise_item)

        insertExercisePresenter.init(applicationContext)

        var load = insertExercisePresenter.getRouter().load
        if (load != null && load){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            if (!insertExercisePresenter.getRouter().load) {
                insertExercisePresenter.saveData(exerciseText.text.toString(), initialWeightText.text.toString().toInt(),
                    finalWeightText.text.toString().toInt())
            } else {
                insertExercisePresenter.editData(exerciseText.text.toString(), initialWeightText.text.toString().toInt(),
                    finalWeightText.text.toString().toInt())
            }
            insertExercisePresenter.goToCreate()
        }
    }

    private fun loadData() {
        var exercise = insertExercisePresenter.getRouter().exercise
        exerciseText.text = Editable.Factory.getInstance().newEditable(exercise.exerciseName)
        initialWeightText.text = Editable.Factory.getInstance().newEditable(exercise.initialWeight.toString())
        finalWeightText.text = Editable.Factory.getInstance().newEditable(exercise.finalWeight.toString())
    }
}
