package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenter.InsertTrainingPresenter
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingDayActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val insertTrainingPresenter = InsertTrainingPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_training_day_item)

        insertTrainingPresenter.initDatabase()
        setDaysSpinner()
        insertTrainingPresenter.loadIntentData()
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            insertTrainingPresenter.saveData()
        }
    }

    private fun setDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTrainingDays.onItemSelectedListener = this
        spinnerTrainingDays.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        insertTrainingPresenter.spinnerTrainingDay = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
