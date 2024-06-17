import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketManager {

    private lateinit var socket: Socket

    init {
        try {
            socket = IO.socket("http://0.0.0.0:1984/connect")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    fun connect() {
        socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun on(event: String, listener: (Array<Any>) -> Unit) {
        socket.on(event) { args -> listener(args) }
    }

    fun emit(event: String, data: Any) {
        socket.emit(event, data)
    }
}
