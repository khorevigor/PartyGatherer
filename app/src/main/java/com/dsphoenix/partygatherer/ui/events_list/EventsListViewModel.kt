package com.dsphoenix.partygatherer.ui.events_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsphoenix.partygatherer.data.EventsRepository
import com.dsphoenix.partygatherer.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsListViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
): ViewModel() {

    private val _events: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    val events = _events.asStateFlow()

    init {
        viewModelScope.launch {
            _events.value = eventsRepository.getAllEvents()
        }
    }
}
