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

private const val TABLE_MESSAGES = "messages"
private const val PATH_ENTITIES = "entities"

class CloudMessagesSource(private val storage: CloudStorage) : MessagesSource {
    override fun getMessages(userName: String, completion: (List<Message>?) -> Unit, observation: (List<Message>?) -> Unit) {
        storage.readArray("$TABLE_MESSAGES/$userName/$PATH_ENTITIES", completion = { entities, throwable ->
            Logger.d(throwable)
            entities?.map { it.toMessage }?.let { messages ->
                completion(messages.sortedBy { it.timestamp })
            }?: completion(null)
        }, observation = { added, _, _, _ ->
            observation(added?.map { it.toMessage })
        })
    }

    override fun sendMessage(message: Message, completion: (Boolean) -> Unit) {
        storage.writeData("$TABLE_MESSAGES/${message.from}/$PATH_ENTITIES/${message.timestamp}", message.toMap) { _, throwable ->
            Logger.d(throwable)

            // We need add another message to recipient as well.
            storage.writeData("$TABLE_MESSAGES/${message.to}/$PATH_ENTITIES/${message.timestamp}", message.toMap) { result, _ ->
                completion(result)
            }
        }
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