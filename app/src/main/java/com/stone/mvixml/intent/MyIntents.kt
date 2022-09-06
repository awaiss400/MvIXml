package com.stone.mvixml.intent

import com.stone.mvixml.model.Posts
//represts action like mjy jasy abi posts chahyen
sealed class MyIntents{
object Getpost:MyIntents()
   class postdata(val posts: Posts):MyIntents()


}


