import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {

    measureTimeMillis {
        runBlocking {
            repeat(1_000) { i ->
                launch {
                    call(i)
                }
            }
        }
    }.also {
        print("Result = Coroutines finished in $it ms")
    }


}

private suspend fun call(i: Int): Int {
    delay(1000L)
    return i
}

