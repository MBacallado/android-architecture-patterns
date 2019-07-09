package com.manuelbacallado.gymprogress.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.manuelbacallado.gymprogress.R
import com.manuelbacallado.gymprogress.presenters.InsertRoutinePresenter
import com.manuelbacallado.gymprogress.routers.InsertRoutineRouter
import kotlinx.android.synthetic.main.insert_routine_item.*

class InsertRoutineActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var spinnerDay: String = ""
    var spinnerTypes: String = ""

    private var insertRoutinePresenter = InsertRoutinePresenter();
    private var insertRoutineRouter = InsertRoutineRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_routine_item)

        setTrainingDaysSpinner()
        setTrainingTypeSpinner()

        insertRoutinePresenter.init(applicationContext)
        insertRoutineRouter.initData()
        if (insertRoutineRouter.load != null && insertRoutineRouter.load) {
            loadData()
        }
        saveData()
    }

    private fun saveData() {
        buttonSave.setOnClickListener() {
            if (!insertRoutineRouter.load) {
                insertRoutinePresenter.saveData(routineText.text.toString(), startDateText.text.toString(),
                    finishDateText.text.toString(), spinnerDay.toInt(), spinnerTypes)
            } else {
                insertRoutinePresenter.editData(routineText.text.toString(), startDateText.text.toString(),
                    finishDateText.text.toString(), spinnerDay.toInt(), spinnerTypes, insertRoutineRouter.routine.routineId)
            }
            insertRoutineRouter.goToCreate()
        }
    }

    private fun loadData() {
        routineText.text = Editable.Factory.getInstance().newEditable(insertRoutineRouter.routine.name)
        startDateText.text = Editable.Factory.getInstance().newEditable(insertRoutineRouter.routine.startDate)
        finishDateText.text = Editable.Factory.getInstance().newEditable(insertRoutineRouter.routine.finishDate)
        fillTrainingDaysSpinner(insertRoutineRouter.routine.trainingDays)
        fillTrainingTypeSpinner(insertRoutineRouter.routine.trainingTypes)
    }

    private fun setTrainingDaysSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDays.onItemSelectedListener = this
        spinnerDays.adapter = adapter
    }

    private fun fillTrainingDaysSpinner(trainingDays: Int) {
        val numbersArray = resources.getStringArray(R.array.numbers)
        for (i in 0..numbersArray.size-1) {
            if (numbersArray.get(i).toInt() == trainingDays) {
                spinnerDays.setSelection(i)
            }
        }
    }

    private fun setTrainingTypeSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.trainingType, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerType.onItemSelectedListener = this
        spinnerType.adapter = adapter
    }

    private fun fillTrainingTypeSpinner(trainingTypes: String) {
        val typesArray = resources.getStringArray(R.array.trainingType)
        for (i in 0..typesArray.size-1) {
            if (typesArray.get(i).equals(trainingTypes)) {
                spinnerType.setSelection(i)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            spinnerDays.id -> spinnerDay = parent?.getItemAtPosition(position).toString()
            spinnerType.id -> spinnerTypes = parent?.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
