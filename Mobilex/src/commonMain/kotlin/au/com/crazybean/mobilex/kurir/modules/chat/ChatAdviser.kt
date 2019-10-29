package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch
import au.com.crazybean.mobilex.kurir.modules.base.fullName

class ChatAdviser(scene: ChatScene?, worker: ChatWorker) : Adviser<ChatScene, ChatWorker>(scene, worker) {
    private var user: User? = null

    private val entities by lazy {
        mutableListOf<Message>()
    }

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        user = params.fetch(Extra.USER, User.serializer())
        val desc = params.get(Extra.DESC) as String?

        user?.let {
            scene?.showName(worker.myEmail, it.fullName)
            worker.messages.observe(this) { result ->
                    result?.takeIf { it.isNotEmpty() }?.let { messages ->
                        onNewMessage(messages)
                    }?: scene?.showEmpty()
                }
        }?: scene?.showEmpty()
    }

    fun onSendClick(content: String?) {
        content?.takeIf { it.isNotEmpty() }?.let {
            scene?.clearText()
            worker.sendMessage(it, user?.email?: "")
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