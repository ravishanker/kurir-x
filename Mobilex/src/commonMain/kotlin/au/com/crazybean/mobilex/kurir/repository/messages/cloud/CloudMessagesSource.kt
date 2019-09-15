package au.com.crazybean.mobilex.kurir.repository.messages.cloud

import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesSource
import au.com.crazybean.mobilex.kurir.storage.CloudStorage

class CloudMessagesSource(private val storage: CloudStorage) : MessagesSource {
    override fun getMessages(userName: String, callback: (List<Message>?) -> Unit) {
    }

    override fun sendMessage(from: String, to: String, message: Message, callback: (Boolean) -> Unit) {
    }
}