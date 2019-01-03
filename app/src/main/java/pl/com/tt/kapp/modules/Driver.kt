package pl.com.tt.kapp.modules

interface Driver {

    fun enable()
    fun disable()
    fun isEnabled() : Boolean
    fun scan()

}