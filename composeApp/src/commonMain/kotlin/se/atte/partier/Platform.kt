package se.atte.partier

interface Platform {
    val name: String
    val type : Type

    enum class Type {
        Android, IOS, Web
    }
}

expect fun getPlatform(): Platform