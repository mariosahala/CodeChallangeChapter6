package com.example.batuguntingkertas.ui.chatBotActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.batuguntingkertas.R
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.KIRIM
import com.example.batuguntingkertas.ui.chatBotActivity.utils.KonteksPesan.TERIMA

@Suppress("DEPRECATION")
class ChatBotAdapter : RecyclerView.Adapter<ChatBotAdapter.PesanViewHolder>() {

    var listPesan = mutableListOf<ChatBotData>()

    inner class PesanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                listPesan.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesanViewHolder {
        return PesanViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pesan_item,parent,false))
    }

    override fun onBindViewHolder(holder: PesanViewHolder, position: Int) {
        val posisiPesan = listPesan[position]

        when(posisiPesan.id){
            KIRIM -> {
                holder.itemView.findViewById<TextView>(R.id.tvPesanUser).apply {
                    text = posisiPesan.pesan
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tvPesanBot).visibility = View.GONE
            }
            TERIMA -> {
                holder.itemView.findViewById<TextView>(R.id.tvPesanBot).apply {
                    text = posisiPesan.pesan
                    visibility = View.VISIBLE
                }
                holder.itemView.findViewById<TextView>(R.id.tvPesanUser).visibility = View.GONE
            }
        }

    }

    override fun getItemCount(): Int {
        return listPesan.size
    }

    fun pesanMasuk(chatBotData: ChatBotData){
        this.listPesan.add(chatBotData)
        notifyItemInserted(listPesan.size)
    }

}