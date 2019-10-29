package au.com.crazybean.mobilex.kurir.modules.contacts

import android.content.Context
import android.view.View
import android.widget.TextView
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.User

class ContactsAdapter(delegate: RecyclerUtils.Delegate<User>) : RecyclerUtils.Adapter<User>(delegate) {
    init {
        addHolderType(R.layout.viewholder_contact, ContactHolder::class.java)
    }

    private class ContactHolder(itemView: View) : RecyclerUtils.ViewHolder<User>(itemView) {
        private val firstNameLabel = itemView.findViewById<TextView>(R.id.first_name_label)
        private val lastNameLabel = itemView.findViewById<TextView>(R.id.last_name_label)
        private val emailLabel = itemView.findViewById<TextView>(R.id.email_label)

        override fun setData(context: Context, data: User?) {
            super.setData(context, data)
            firstNameLabel?.text = data?.firstName
            lastNameLabel?.text = data?.lastName
            emailLabel?.text = data?.email
        }
    }
}