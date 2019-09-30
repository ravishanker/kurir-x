package au.com.crazybean.mobilex.kurir.modules.tasks.impl

import android.content.Context
import android.view.View
import android.widget.TextView
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Task

class TasksAdapter(delegate: RecyclerUtils.Delegate<Task>) : RecyclerUtils.Adapter<Task>(delegate) {
    init {
        addHolderType(R.layout.viewholder_task, TaskHolder::class.java)
    }

    /**
     * TaskHolder
     */
    private class TaskHolder(itemView: View) : RecyclerUtils.ViewHolder<Task>(itemView) {
        private val addressLabel = itemView.findViewById<TextView?>(R.id.origin_dest_label)
        private val descLabel = itemView.findViewById<TextView?>(R.id.desc_label)

        override fun setData(context: Context, data: Task?) {
            super.setData(context, data)

            data?.let {
                addressLabel?.text = "${it.origin.city} ${it.origin.country} -> ${it.dest.city} ${it.dest.country}"
                descLabel?.text = it.parcel.desc
            }
        }
    }
}