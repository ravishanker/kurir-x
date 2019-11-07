package au.com.crazybean.mobilex.kurir.modules.chat

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.EditorWatcher
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ChatActivity : BaseActivity<ChatActor>(), ChatScene {
    override val actor: ChatActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_chat

    private var editor: EditText? = null
    private var chatLabel: TextView? = null
    private var sendButton: ImageView? = null
    private var recyclerView: RecyclerView? = null

    private var adapter: MessagesAdapter? = null

    override fun onViewLoad() {
        super.onViewLoad()
        editor = findViewById(R.id.message_editor)
        chatLabel = findViewById(R.id.chat_with_label)
        sendButton = findViewById(R.id.send_button)
        editor?.addTextChangedListener(object : EditorWatcher {
            override fun onTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int) {
                sendButton?.setImageResource(if (var1?.toString().isNullOrEmpty()) R.drawable.ic_send else R.drawable.ic_send_on)
            }
        })
        sendButton?.setOnClickListener {
            editor?.text?.toString()?.takeIf { it.isNotEmpty() }?.let {
                actor?.onSendClick(it)
            }
        }
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.adapter = adapter
    }

    override fun showName(myEmail: String, name: String, initial: String) {
        if (recyclerView?.adapter == null) {
            adapter = MessagesAdapter(myEmail, initial).also {
                recyclerView?.adapter = it
            }
        }
        chatLabel?.text = getString(R.string.label_chat_with, name)
    }

    override fun showMessages(messages: List<Message>) {
        adapter?.addEntities(messages)
        recyclerView?.smoothScrollToPosition(adapter?.itemCount?: 0)
    }

    override fun showMore(messages: List<Message>) {
        adapter?.addEntities(messages, true)
        recyclerView?.smoothScrollToPosition(adapter?.itemCount?: 0)
    }

    override fun showEmpty() {
    }

    override fun clearText() {
        editor?.setText("")
    }
}

