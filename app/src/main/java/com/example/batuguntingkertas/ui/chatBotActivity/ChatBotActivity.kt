package com.example.batuguntingkertas.ui.chatBotActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.ui.chatBotActivity.utils.BotRespon
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.KIRIM
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.MAIN
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.MAIN_KOM
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.MAIN_PEMAIN
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.OPEN_SEARCH
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.OPEN_TUTORIAL
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.TERIMA
import com.example.batuguntingkertas.ui.chatBotActivity.utils.Time
import com.example.batuguntingkertas.ui.menu.MenuActivity
import com.example.batuguntingkertas.ui.play.VsCpu
import com.example.batuguntingkertas.ui.play.VsPlayer
import kotlinx.coroutines.*

class ChatBotActivity : AppCompatActivity() {

    private lateinit var adapter: ChatBotAdapter
    private lateinit var rvChat: RecyclerView
    private lateinit var etPesanUser: EditText
    private lateinit var btnKirim: Button
    private lateinit var ivBackButton : ImageView
    var listPesan = mutableListOf<ChatBotData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)


        rvChat = findViewById(R.id.rvChat)
        etPesanUser = findViewById(R.id.etPesanUser)
        btnKirim = findViewById(R.id.btnKirim)
        ivBackButton = findViewById(R.id.ivBackButton)
        ivBackButton.setOnClickListener{
            startActivity(Intent(this,MenuActivity::class.java))
        }

        recyclerView()
        buttonKirim()

        pesanUtama("Halo! kenalin ini CaBo, yuk ngobrol seputaran gamenya! Kamu bisa coba keyword dibawah untuk memulai permainan atau informasi cara bermain\n1.Halaman Utama\n2.Main Dengan Teman\n3.Main Dengan Komputer\n4.Cari\n5.Tutorial")
    }

    private fun buttonKirim() {
        btnKirim.setOnClickListener {
            kirimPesan()
        }
        etPesanUser.setOnClickListener {
            GlobalScope.launch {
                delay(200)
                withContext(Dispatchers.Main) {
                    rvChat.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = ChatBotAdapter()
        rvChat.adapter = adapter
        rvChat.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rvChat.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun kirimPesan() {
        val pesan = etPesanUser.text.toString()
        val timestamp = Time.timeStamp()
        if (pesan.isNotEmpty()) {
            listPesan.add(ChatBotData(pesan, KIRIM, timestamp))
            etPesanUser.setText("")
            adapter.pesanMasuk(ChatBotData(pesan, KIRIM, timestamp))
            rvChat.scrollToPosition(adapter.itemCount - 1)
            responBot(pesan)
        }
    }

    private fun responBot(pesan: String) {
        val timestamp = Time.timeStamp()
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                val responBot = BotRespon.respon(pesan)
                listPesan.add(ChatBotData(responBot, TERIMA, timestamp))
                adapter.pesanMasuk(ChatBotData(responBot, TERIMA, timestamp))
                rvChat.scrollToPosition(adapter.itemCount - 1)
                when (responBot) {
                    OPEN_SEARCH -> {
                        val link = Intent(Intent.ACTION_VIEW)
                        val pencarian: String? = pesan.substringAfterLast("Cari")
                        link.data = Uri.parse("https://www.google.com/search?&q=$pencarian")
                        startActivity(link)
                    }
                    OPEN_TUTORIAL -> {
                        val link = Intent(Intent.ACTION_VIEW)
                        link.data = Uri.parse("https://www.youtube.com/watch?v=Cl8EwB1DH6k")
                        startActivity(link)
                    }
                    MAIN -> {
                        startActivity(Intent(this@ChatBotActivity, MenuActivity::class.java))
                    }
                    MAIN_PEMAIN ->{
                        startActivity(Intent(this@ChatBotActivity, VsPlayer::class.java))
                    }
                    MAIN_KOM ->{
                        startActivity(Intent(this@ChatBotActivity, VsCpu::class.java))
                    }
                }


            }
        }
    }

    private fun pesanUtama(pesan: String) {
        GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                adapter.pesanMasuk(ChatBotData(pesan, TERIMA, timeStamp))
                rvChat.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}