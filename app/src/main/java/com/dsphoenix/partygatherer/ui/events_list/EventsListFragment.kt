package com.dsphoenix.partygatherer.ui.events_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dsphoenix.partygatherer.databinding.FragmentEventsListBinding
import com.dsphoenix.partygatherer.ui.add_edit_event.collectLatestLifeCycleFlow
import com.dsphoenix.partygatherer.ui.utils.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsListFragment: ViewBindingFragment<FragmentEventsListBinding>(FragmentEventsListBinding::inflate) {

    private val viewModel: EventsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        binding.apply {
            rvEvents.adapter = EventsAdapter()

            collectLatestLifeCycleFlow(viewModel.events) { events ->
                (rvEvents.adapter as EventsAdapter).setData(events)
            }
        }
    }
}
