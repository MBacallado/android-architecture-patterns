package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenters.InsertTrainingPresenter
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingDayActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinnerTrainingDay: String = ""

    private var insertTrainingPresenter = InsertTrainingPresenter(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_training_day_item)

        insertTrainingPresenter.init(applicationContext)
        setDaysSpinner()
        var load = insertTrainingPresenter.getRouter().load
        if (load != null && load){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            if (!insertTrainingPresenter.getRouter().load) {
                insertTrainingPresenter.saveData(spinnerTrainingDay, timeText.text.toString().toInt(), groupText.text.toString())
            } else {
                insertTrainingPresenter.editData(spinnerTrainingDay, timeText.text.toString().toInt(), groupText.text.toString())
            }
            insertTrainingPresenter.goToCreate()
        }
    }

    private fun loadData() {
        var training = insertTrainingPresenter.getRouter().trainingDay
        timeText.text = Editable.Factory.getInstance().newEditable(training.timeAmount.toString())
        groupText.text = Editable.Factory.getInstance().newEditable(training.group)
        fillTrainingDaysNameSpinner(training.day);
    }

    private fun setDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTrainingDays.onItemSelectedListener = this
        spinnerTrainingDays.adapter = adapter
    }

    private fun fillTrainingDaysNameSpinner(trainingDaysName: String) {
        val trainingDaysArray = resources.getStringArray(R.array.days)
        for (i in 0..trainingDaysArray.size-1) {
            if (trainingDaysArray.get(i).equals(trainingDaysName)) {
                spinnerTrainingDays.setSelection(i)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinnerTrainingDay = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
