package au.com.crazybean.mobilex.kurir.repository.messages

import au.com.crazybean.mobilex.kurir.data.model.Message

interface MessagesSource {
    fun getMessages(userName: String, callback: (List<Message>?) -> Unit)
    fun sendMessage(from: String, to: String, message: Message, callback: (Boolean)-> Unit)
}