package au.com.crazybean.mobilex.kurir.repository.messages

import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.data.model.Message

class MessagesRepository(private val messagesSource: MessagesSource) : Repository() {
    fun getMessages(toEmail: String, completion: (List<Message>?)-> Unit, observation: (List<Message>?)-> Unit) {
        messagesSource.getMessages(toEmail, completion, observation)
    }

    fun sendMessage(message: Message, completion: (Boolean) -> Unit) {
        messagesSource.sendMessage(message) {
            completion(it)
        }
    }
}