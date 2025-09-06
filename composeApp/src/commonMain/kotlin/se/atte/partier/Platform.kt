package se.atte.partier

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform