package pl.com.tt.kapp.modules.abstraction

interface NetworkingAdapter {
    fun enable()
    fun disable()
    fun scan()
    fun isEnabled(): Boolean
}