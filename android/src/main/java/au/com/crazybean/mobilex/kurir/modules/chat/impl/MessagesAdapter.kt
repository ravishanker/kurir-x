package au.com.crazybean.mobilex.kurir.modules.chat.impl

import android.content.Context
import android.view.View
import android.widget.TextView
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Message
import au.com.crazybean.mobilex.kurir.modules.base.relativeTime

private const val TYPE_SENDER = 0
private const val TYPE_RECEIVER = 1

class MessagesAdapter(var myEmail: String? = null) : RecyclerUtils.Adapter<Message>() {
    init {
        addHolderType(TYPE_SENDER, R.layout.viewholder_sender_msg, SenderHolder::class.java)
        addHolderType(TYPE_RECEIVER, R.layout.viewholder_reciver_msg, ReceiverHolder::class.java)
    }

    override fun getViewType(entity: Message): Int {
        return if (entity.from.equals(myEmail, true)) TYPE_SENDER else TYPE_RECEIVER
    }

    /**
     * MessageHolder
     */
    private open class MessageHolder(itemView: View) : RecyclerUtils.ViewHolder<Message>(itemView) {
        private val messageView: TextView? = itemView.findViewById(R.id.message_content)
        private val timeLabel: TextView? = itemView.findViewById(R.id.relative_time_label)

        override fun setData(context: Context, data: Message?) {
            super.setData(context, data)
            messageView?.text = data?.content
            timeLabel?.text = data?.timestamp?.relativeTime ?: ""
        }
    }

    private class SenderHolder(itemView: View) : MessageHolder(itemView)

    private class ReceiverHolder(itemView: View) : MessageHolder(itemView)
}