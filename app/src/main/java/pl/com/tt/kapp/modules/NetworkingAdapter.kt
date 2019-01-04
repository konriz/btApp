package pl.com.tt.kapp.modules

interface NetworkingAdapter {
    fun enable()
    fun disable()
    fun scan()
    fun isEnabled(): Boolean
}