package com.dsphoenix.partygatherer.ui.add_edit_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsphoenix.partygatherer.data.EventsRepository
import com.dsphoenix.partygatherer.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.*
import javax.inject.Inject

@HiltViewModel
class AddEditEventViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
): ViewModel() {

    private val _time: MutableStateFlow<LocalTime> = MutableStateFlow(LocalTime.now())
    val time = _time.asStateFlow()

    private val _date: MutableStateFlow<LocalDate> = MutableStateFlow(LocalDate.now())
    val date = _date.asStateFlow()

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description: MutableStateFlow<String> = MutableStateFlow("")
    val description = _description.asStateFlow()
    
    private val _location: MutableStateFlow<String> = MutableStateFlow("")
    val location = _location.asStateFlow()

    fun createEvent() {
        val event = Event(
            timestamp = LocalDateTime.of(_date.value, time.value).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            title = _title.value,
            description = _description.value,
            location = _location.value
        )
        viewModelScope.launch {
            eventsRepository.addEvent(event)
        }
    }

    fun setTime(hour: Int, minute: Int) {
        _time.value = LocalTime.of(hour, minute)
    }

    fun setDate(year: Int, month: Int, day: Int) {
        _date.value = LocalDate.of(year, month + 1, day)
    }

    fun setTitle(text: String) {
        _title.value = text
    }

    fun setDescription(text: String) {
        _description.value = text
    }

    fun setLocation(text: String) {
        _location.value = text
    }
}
