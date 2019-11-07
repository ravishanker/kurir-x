package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch
import au.com.crazybean.mobilex.kurir.modules.base.fullName

class ChatActor(scene: ChatScene?, wrapper: ChatWrapper) : Actor<ChatScene, ChatWrapper>(scene, wrapper) {
    private var user: User? = null

    private val entities by lazy {
        mutableListOf<Message>()
    }

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        user = params.fetch(Extra.USER, User.serializer())

        user?.let {
            scene?.showName(wrapper.myEmail, it.fullName, "${it.firstName?.first()}${it.lastName?.first()}".toUpperCase())
            wrapper.messages.observe(this) { result ->
                    result?.takeIf { it.isNotEmpty() }?.let { messages ->
                        onNewMessage(messages)
                    }?: scene?.showEmpty()
                }
        }?: scene?.showEmpty()
    }

    fun onSendClick(content: String?) {
        content?.takeIf { it.isNotEmpty() }?.let {
            scene?.clearText()
            wrapper.sendMessage(it, user?.email?: "")
        }
    }

    private fun onNewMessage(messages: List<Message>) {
        entities.lastOrNull()?.let { last ->
            messages.filter { it.timestamp > last.timestamp }.takeIf { it.isNotEmpty() }?.let { latest ->
                entities.addAll(latest)
                scene?.showMore(latest)
            }
        }?: scene?.showMessages(messages).also {
            entities.addAll(messages)
        }
    }
}