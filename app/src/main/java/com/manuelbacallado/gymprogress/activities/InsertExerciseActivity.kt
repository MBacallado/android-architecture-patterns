package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenters.InsertExercisePresenter
import com.manuelbacallado.gymprogress.routers.InsertExerciseRouter
import kotlinx.android.synthetic.main.insert_exercise_item.*

class InsertExerciseActivity : AppCompatActivity() {

    private var insertExercisePresenter = InsertExercisePresenter();
    private var insertExerciseRouter = InsertExerciseRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_exercise_item)

        insertExercisePresenter.init(applicationContext)
        insertExerciseRouter.initData()
        if (insertExerciseRouter.load != null && insertExerciseRouter.load){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            if (!insertExerciseRouter.load) {
                insertExercisePresenter.saveData(exerciseText.text.toString(), initialWeightText.text.toString().toInt(),
                    finalWeightText.text.toString().toInt(), insertExerciseRouter.parentId)
            } else {
                insertExercisePresenter.editData(exerciseText.text.toString(), initialWeightText.text.toString().toInt(),
                    finalWeightText.text.toString().toInt(), insertExerciseRouter.parentId,
                    insertExerciseRouter.exercise.exerciseId)
            }
            insertExerciseRouter.goToCreate()
        }
    }

    private fun loadData() {
        exerciseText.text = Editable.Factory.getInstance().newEditable(insertExerciseRouter.exercise.exerciseName)
        initialWeightText.text = Editable.Factory.getInstance().newEditable(insertExerciseRouter.exercise.initialWeight.toString())
        finalWeightText.text = Editable.Factory.getInstance().newEditable(insertExerciseRouter.exercise.finalWeight.toString())
    }
}
