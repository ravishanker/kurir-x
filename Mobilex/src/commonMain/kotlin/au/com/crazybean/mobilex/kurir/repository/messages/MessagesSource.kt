package au.com.crazybean.mobilex.kurir.repository.messages

import au.com.crazybean.mobilex.kurir.data.model.Message

interface MessagesSource {
    fun getMessages(userName: String, completion: (List<Message>?) -> Unit, observation: (List<Message>?) -> Unit)
    fun sendMessage(message: Message, completion: (Boolean)-> Unit)
}