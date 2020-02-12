package com.example.wanandroid
import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext

class MyContinuationInterceptor: ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

class MyContinuation<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        println("<MyContinuation> $result")
        continuation.resumeWith(result)
    }
}

suspend fun main(){
        println("thread:${Thread.currentThread()}")
    GlobalScope.launch {
        println("thread:${Thread.currentThread()}")
        withContext(context = MyContinuationInterceptor()){
            println(1)
            delay(100)
            println("thread:${Thread.currentThread()}")

            1
        }
        println("thread:${Thread.currentThread()}")
    }.join()

    println("thread:${Thread.currentThread()}")
    println(6)
}


