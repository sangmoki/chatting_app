package com.sangmoki.chatting_app.Model

data class Chat (
    val name: String,
    val messaage: String,
) {
    operator fun get(position: Int): String {

    }
}