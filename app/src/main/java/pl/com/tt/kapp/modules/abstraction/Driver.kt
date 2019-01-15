package pl.com.tt.kapp.modules.abstraction

abstract class Driver<T>(protected var adapter: NetworkingAdapter) {

    fun enable() {
        adapter.enable()
    }
    fun disable() {
        adapter.disable()
    }
    fun isEnabled() = adapter.isEnabled()

    fun scan() {
        adapter.scan()
    }

    abstract fun onDiscoveryStarted()
    abstract fun onDiscoveryFinished(results : List<T>)

}