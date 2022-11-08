package com.trainee.appinventiv.eccoscreens.viewmodel

import androidx.lifecycle.ViewModel
import com.trainee.appinventiv.eccoscreens.R
import kotlinx.coroutines.flow.MutableStateFlow

class TutorialViewModel : ViewModel() {

    var textListener = MutableStateFlow<String>("")

    var hideTutorial = MutableStateFlow(false)

    fun ki(){
        val k = R.string.t1
    }

}