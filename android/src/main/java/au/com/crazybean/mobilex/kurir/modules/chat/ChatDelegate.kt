package au.com.crazybean.mobilex.kurir.modules.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch
import au.com.crazybean.mobilex.kurir.modules.base.fullName

class ChatDelegate(view: ChatView?, viewModel: ChatViewModel) : Delegate<ChatView, ChatViewModel>(view, viewModel) {
    private var user: User? = null

    private val entities by lazy {
        mutableListOf<Message>()
    }

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        user = params.fetch(Extra.USER, User.serializer())

        user?.let {
            view?.showName(viewModel.myEmail, it.fullName)
            viewModel.messages
                .observe(this, Observer { result ->
                    result?.takeIf { it.isNotEmpty() }?.let { messages ->
                        onNewMessage(messages)
                    }?: view?.showEmpty()
                })
        }?: view?.showEmpty()
    }

    fun onSendClick(content: String?) {
        content?.takeIf { it.isNotEmpty() }?.let {
            view?.clearText()
            viewModel.sendMessage(it, user?.email?: "")
        }
    }

    private fun onNewMessage(messages: List<Message>) {
        entities.lastOrNull()?.let { last ->
            messages.filter { it.timestamp > last.timestamp }.takeIf { it.isNotEmpty() }?.let { latest ->
                entities.addAll(latest)
                view?.showMore(latest)
            }
        }?: view?.showMessages(messages).also {
            entities.addAll(messages)
        }
    }
}