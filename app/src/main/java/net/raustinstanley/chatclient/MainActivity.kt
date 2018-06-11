package net.raustinstanley.chatclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private lateinit var socket: Socket
    private lateinit var btnTest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        socket = IO.socket("http://192.168.1.8:8000")
        socket.connect()

        socket.on("test", { _ ->
            runOnUiThread {
                toast("Ping received")
            }
        })

        btnTest = findViewById(R.id.btn_test)
        btnTest.setOnClickListener {
            socket.emit("test")
        }
    }
}
