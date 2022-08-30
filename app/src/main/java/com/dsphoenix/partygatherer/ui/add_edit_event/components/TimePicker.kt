package com.dsphoenix.partygatherer.ui.add_edit_event.components

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.dsphoenix.partygatherer.ui.add_edit_event.AddEditEventViewModel
import javax.inject.Inject

class TimePicker(
    private val viewModel: AddEditEventViewModel
) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = viewModel.time.value.hour
        val minute = viewModel.time.value.minute

        return TimePickerDialog(context, this, hour, minute, DateFormat.is24HourFormat(context))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        viewModel.setTime(hourOfDay, minute)
    }

}
