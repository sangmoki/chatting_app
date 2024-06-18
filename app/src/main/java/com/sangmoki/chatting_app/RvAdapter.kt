package com.sangmoki.chatting_app

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sangmoki.chatting_app.Model.Chat

class RvAdapter(val chat: Chat) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // 각 RecyclerView의 아이템 뿌려주기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_item_list, parent, false)
        return ViewHolder(view)
    }

    // ViewHolder의 데이터 바인딩 처리
    override fun onBindViewHolder(holder: RvAdapter.ViewHolder, position: Int) {
        holder.bindItems(chat[position])
    }

    // 전체 RecyclerView 개수
    override fun getItemCount(): Chat {
        return chat
    }

    // ViewHolder 생성
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item : String) {

        }
    }
}
