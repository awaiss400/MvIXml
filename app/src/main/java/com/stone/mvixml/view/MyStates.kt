package com.stone.mvixml.view

import com.stone.mvixml.intent.MyIntents
import com.stone.mvixml.model.Posts

sealed class MyStates{
    object Idle: MyStates()
    object Loading: MyStates()
    class Error(val msg:String): MyStates()
    class Success(val data:List<Posts>): MyStates()
}
