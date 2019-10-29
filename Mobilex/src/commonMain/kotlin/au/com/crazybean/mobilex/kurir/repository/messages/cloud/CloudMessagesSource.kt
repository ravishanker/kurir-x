package au.com.crazybean.mobilex.kurir.repository.messages.cloud

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesSource
import au.com.crazybean.mobilex.kurir.storage.CloudStorage

private const val kFrom = "from"
private const val kTo = "to"
private const val kTimestamp = "timestamp"
private const val kContent = "content"
private const val kType = "type"
private const val kId = "id"

private const val PATH = "users/$0/messages"

class CloudMessagesSource(private val storage: CloudStorage) : MessagesSource {
    override fun getMessages(userName: String, completion: (List<Message>?) -> Unit, observation: (List<Message>?) -> Unit) {
        storage.readArray(PATH.replace("$0", userName), completion = { entities, throwable ->
            Logger.d(throwable)
            entities?.map { it.toMessage }?.let { messages ->
                completion(messages.sortedBy { it.timestamp })
            }?: completion(null)
        }, observation = { added, _, _, _ ->
            observation(added?.map { it.toMessage })
        })
    }

    override fun sendMessage(message: Message, completion: (Boolean) -> Unit) {
        message.takeIf { it.from.isNotBlank() && it.to.isNotBlank() }?.let {
            storage.writeData("${PATH.replace("$0", it.from)}/${it.timestamp}", message.toMap) { _, throwable ->
                Logger.d(throwable)

                // We need add another message to recipient as well.
                storage.writeData("${PATH.replace("$0", it.to)}/${it.timestamp}", message.toMap) { result, _ ->
                    completion(result)
                }
            }
        }?: completion(false)
    }

    private val Map<String, Any?>.toMessage: Message
        get() = Message(
            (get(kFrom) as String?)?: "",
            (get(kTo) as String?)?: "",
            (get(kContent) as String?)?: "",
            (get(kType) as Long?)?: -1,
            (get(kTimestamp) as Long?)?: 0L
        )

    private val Message.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kId, from),
            Pair(kFrom, from),
            Pair(kTo, to),
            Pair(kContent, content),
            Pair(kTimestamp, timestamp),
            Pair(kType, type)
        )
}