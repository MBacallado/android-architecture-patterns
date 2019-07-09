package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenters.InsertTrainingPresenter
import com.manuelbacallado.gymprogress.routers.InsertTrainingRouter
import kotlinx.android.synthetic.main.insert_training_day_item.*

class InsertTrainingDayActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinnerTrainingDay: String = ""

    private var insertTrainingPresenter = InsertTrainingPresenter();
    private var insertTrainingRouter = InsertTrainingRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_training_day_item)

        insertTrainingPresenter.init(applicationContext)
        insertTrainingRouter.initData()
        setDaysSpinner()
        if (insertTrainingRouter.load != null && insertTrainingRouter.load){
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            if (!insertTrainingRouter.load) {
                insertTrainingPresenter.saveData(spinnerTrainingDay, timeText.text.toString().toInt(), groupText.text.toString()
                    ,insertTrainingRouter.parentId)
            } else {
                insertTrainingPresenter.editData(spinnerTrainingDay, timeText.text.toString().toInt(), groupText.text.toString(),
                    insertTrainingRouter.trainingDay.trainingDayId, insertTrainingRouter.parentId)
            }
            insertTrainingRouter.goToCreate()
        }
    }

    private fun loadData() {
        timeText.text = Editable.Factory.getInstance().newEditable(insertTrainingRouter.trainingDay.timeAmount.toString())
        groupText.text = Editable.Factory.getInstance().newEditable(insertTrainingRouter.trainingDay.group)
        fillTrainingDaysNameSpinner(insertTrainingRouter.trainingDay.day);
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
