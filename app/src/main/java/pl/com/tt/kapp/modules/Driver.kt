package pl.com.tt.kapp.modules

abstract class Driver(protected var adapter:NetworkingAdapter) {

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

}