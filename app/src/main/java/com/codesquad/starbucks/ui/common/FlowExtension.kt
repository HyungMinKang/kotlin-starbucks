package com.codesquad.starbucks.ui.common

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

object FlowExtension {

    private fun View.clicks(onClick: () -> Unit): Flow<Unit> = callbackFlow {
        setOnClickListener {
            trySend(Unit)
            onClick.invoke()
        }
        awaitClose { setOnClickListener(null) }
    }

    fun View.setClickEvent(uiScope: CoroutineScope, duration: Long = 4000, onClick: () -> Unit, dbUpdate: () -> Unit) {
        clicks(onClick)
            .sample(duration)
            .onEach {
                dbUpdate.invoke()
            }
            .launchIn(uiScope)
    }

}