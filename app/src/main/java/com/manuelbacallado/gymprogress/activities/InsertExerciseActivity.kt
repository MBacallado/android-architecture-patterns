package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenter.InsertExercisePresenter
import kotlinx.android.synthetic.main.insert_exercise_item.*

class InsertExerciseActivity : AppCompatActivity() {

    private val insertExercisePresenter = InsertExercisePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_exercise_item)

        insertExercisePresenter.initDatabase()
        insertExercisePresenter.loadIntentData()
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            insertExercisePresenter.saveData()
        }
    }
}
