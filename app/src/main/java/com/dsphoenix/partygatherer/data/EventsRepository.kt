package com.dsphoenix.partygatherer.data

import com.dsphoenix.partygatherer.data.firebase.FirestoreService
import com.dsphoenix.partygatherer.model.Event
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val firestore: FirestoreService
) {

    suspend fun addEvent(event: Event) = firestore.writeEvent(event)
}
