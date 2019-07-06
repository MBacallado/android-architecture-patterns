package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenter.InsertRoutinePresenter
import kotlinx.android.synthetic.main.insert_routine_item.*

class InsertRoutineActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val insertRoutinePresenter = InsertRoutinePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_routine_item)

        insertRoutinePresenter.initDatabase()
        setTrainingDaysSpinner()
        setTrainingTypeSpinner()
        insertRoutinePresenter.loadIntentData()
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            insertRoutinePresenter.saveData()
        }
    }

    private fun setTrainingDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDays.onItemSelectedListener = this
        spinnerDays.adapter = adapter
    }

    private fun setTrainingTypeSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.trainingType, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.onItemSelectedListener = this
        spinnerType.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            spinnerDays.id -> insertRoutinePresenter.spinnerDay = parent?.getItemAtPosition(position).toString()
            spinnerType.id -> insertRoutinePresenter.spinnerTypes = parent?.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
