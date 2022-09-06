package com.stone.mvixml.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.mvixml.intent.MyIntents
import com.stone.mvixml.intent.MyIntents.postdata
import com.stone.mvixml.view.MyStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(val myRepository: MyRepository)   :ViewModel() {
val intents:Channel<MyIntents> =Channel(Channel.UNLIMITED)

private val mystate=MutableStateFlow<MyStates>(MyStates.Idle)
        val state:StateFlow<MyStates> =mystate
    val _words = MutableStateFlow(mutableListOf<Posts>())
    val mydata=_words.value

    init {
        intenthandler()
    }

    private fun intenthandler() {
       viewModelScope.launch {
           intents.consumeEach {
               when(it){
                   MyIntents.Getpost -> {
                       mystate.value=MyStates.Loading
                       try {

                           mystate.value=MyStates.Success(data = myRepository.getpostt())
                       }catch (e:Exception){
mystate.value=MyStates.Error(e.message.toString())
                       }
                   }
                   is postdata -> {
                       mystate.value=MyStates.Loading
                       try {
                           mystate.value=MyStates.Success(data = myRepository.postd(Posts(1,2,"ghf","gf")))
                       }catch (e:Exception){
                           mystate.value=MyStates.Error(e.message.toString())
                       }
                   }
               }
           }
       }
    }


}