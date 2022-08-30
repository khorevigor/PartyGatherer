package com.dsphoenix.partygatherer.data.firebase

import android.util.Log
import com.dsphoenix.partygatherer.model.Event
import com.dsphoenix.partygatherer.utils.TAG
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val db = FirebaseFirestore.getInstance()

    suspend fun writeEvent(event: Event) {
        try {
            db.collection(Constants.EVENTS_COLLECTION).add(event).await()
        } catch (cause: FirebaseFirestoreException) {
            Log.d(TAG, cause.toString())
        }
    }

    suspend fun getEventsCollection(): List<Event> {
        return db.collection(Constants.EVENTS_COLLECTION).get().await().map { snapshot ->
            snapshot.toObject()
        }
    }
}
