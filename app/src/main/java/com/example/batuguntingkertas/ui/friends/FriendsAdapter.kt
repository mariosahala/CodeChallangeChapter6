package com.example.batuguntingkertas.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.data.database.FriendsEntity
import kotlinx.android.synthetic.main.friends_item.view.*

class FriendsAdapter(val friends: MutableList<FriendsEntity>, val listener: OnListener) :
    RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.friends_item, parent, false)
        return FriendsViewHolder(itemView)
    }

    override fun getItemCount() = friends.size

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend = friends[position]
        holder.view.tvName.text = friend.name
        holder.view.tvEmail.text = friend.email

        holder.view.tvEdit.setOnClickListener {
            listener.onUpdate(friend)
        }

        holder.view.tvDelete.setOnClickListener {
            listener.onDelete(friend)
        }
    }

    class FriendsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: MutableList<FriendsEntity>) {
        friends.clear()
        friends.addAll(list)
        notifyDataSetChanged()
    }

    interface OnListener {
        fun onUpdate(friends: FriendsEntity)
        fun onDelete(friends: FriendsEntity)
    }

}