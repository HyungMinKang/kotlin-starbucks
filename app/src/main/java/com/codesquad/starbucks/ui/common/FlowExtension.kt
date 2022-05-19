package com.codesquad.starbucks.ui.common

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

object FlowExtension {

    fun View.clicks(): Flow<Unit> = callbackFlow {
        setOnClickListener {
            trySend(Unit)
        }
        awaitClose { setOnClickListener(null) }
    }

    fun View.setClickEvent(uiScope:CoroutineScope, duration:Long= 1000, onClick: ()->Unit){
        clicks()
            .sample(duration)
            .onEach {
                println("clicked")
                onClick.invoke()
            }
            .launchIn(uiScope)
    }

}