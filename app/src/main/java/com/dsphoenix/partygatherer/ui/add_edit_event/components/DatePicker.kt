package com.dsphoenix.partygatherer.ui.add_edit_event.components

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.dsphoenix.partygatherer.ui.add_edit_event.AddEditEventViewModel

class DatePicker(
    private val viewModel: AddEditEventViewModel
) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = viewModel.date.value.year
        val month = viewModel.date.value.month.value
        val day = viewModel.date.value.dayOfMonth

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.setDate(year, month, dayOfMonth)
    }
}
