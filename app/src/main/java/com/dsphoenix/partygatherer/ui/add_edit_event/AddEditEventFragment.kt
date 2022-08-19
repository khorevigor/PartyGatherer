package com.dsphoenix.partygatherer.ui.add_edit_event

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dsphoenix.partygatherer.databinding.FragmentAddEditEventBinding
import com.dsphoenix.partygatherer.ui.add_edit_event.components.DatePicker
import com.dsphoenix.partygatherer.ui.add_edit_event.components.TimePicker
import com.dsphoenix.partygatherer.ui.utils.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class AddEditEventFragment :
    ViewBindingFragment<FragmentAddEditEventBinding>(FragmentAddEditEventBinding::inflate) {

    private val viewModel: AddEditEventViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        binding.apply {
            btnTimePicker.setOnClickListener { showTimePicker() }
            collectLatestLifeCycleFlow(viewModel.time) { time ->
                btnTimePicker.text = time.format(DateTimeFormatter.ofPattern("H:mm"))
            }

            collectLatestLifeCycleFlow(viewModel.date) { date ->
                btnDatePicker.text = date.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
            }

            collectLatestLifeCycleFlow(viewModel.title) { text ->
                if (etTitle.text.toString() != text) {
                    etTitle.setText(text)
                }
            }

            collectLatestLifeCycleFlow(viewModel.description) { text ->
                if (etDescription.text.toString() != text) {
                    etDescription.setText(text)
                }
            }

            collectLatestLifeCycleFlow(viewModel.location) { text ->
                if (etLocation.text.toString() != text) {
                    etLocation.setText(text)
                }
            }

            etTitle.doAfterTextChanged { viewModel.setTitle(it.toString()) }
            etDescription.doAfterTextChanged { viewModel.setDescription(it.toString()) }
            etLocation.doAfterTextChanged { viewModel.setLocation(it.toString()) }

            btnDatePicker.setOnClickListener { showDatePicker() }
            btnCreate.setOnClickListener { viewModel.createEvent() }
        }
    }

    private fun showTimePicker() {
        TimePicker(viewModel).show(parentFragmentManager, "timePicker")
    }

    private fun showDatePicker() {
        DatePicker(viewModel).show(parentFragmentManager, "datePicker")
    }
}

fun <T> Fragment.collectLatestLifeCycleFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        flow.collectLatest(collector)
    }
}
