package se.atte.partier

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val type: Platform.Type = Platform.Type.Web
}

actual fun getPlatform(): Platform = WasmPlatform()