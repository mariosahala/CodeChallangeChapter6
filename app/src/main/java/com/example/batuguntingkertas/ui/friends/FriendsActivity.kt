package com.example.batuguntingkertas.ui.friends

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.data.database.BigDatabase
import com.example.batuguntingkertas.data.database.FriendsEntity
import com.example.batuguntingkertas.ui.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_friends.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendsActivity : AppCompatActivity() {

    val db = BigDatabase.getInstance(this)
    val dao = db?.friendsDao()
    lateinit var friendAdapter: FriendsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        setListener()
        setRecycler()
    }

    override fun onStart() {
        super.onStart()
        reloadData()
    }

    fun reloadData() {
        GlobalScope.launch {
            val friends = dao?.getAllFriends()
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    friends?.let { friendAdapter.setData(it) }
                }
            }
        }
    }

    fun setListener() {
        btnTambahTeman?.setOnClickListener {
            startActivity(Intent(applicationContext, EditFriends::class.java))
        }
        fabClosed?.setOnClickListener {
            startActivity(Intent(applicationContext, MenuActivity::class.java))
        }
    }

    fun intentEdit(id: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditFriends::class.java)
                .putExtra("send_id", id)
                .putExtra("intent_type", intentType)
        )
    }

    fun setRecycler() {
        friendAdapter = FriendsAdapter(mutableListOf(), object : FriendsAdapter.OnListener {
            override fun onUpdate(friends: FriendsEntity) {
                intentEdit(friends.idFriend, 1)
            }

            override fun onDelete(friends: FriendsEntity) {
                GlobalScope.launch {
                    dao?.deleteFriend(friends)
                    reloadData()
                }
            }

        })
        rvListFriends.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = friendAdapter
        }
    }
}
