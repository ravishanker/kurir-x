package au.com.crazybean.mobilex.kurir.modules.base

import android.text.format.DateUtils
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.data.model.User

val User.fullName: String
    get() = "$firstName $lastName"

val Long.relativeTime: String
    get() = DateUtils.getRelativeTimeSpanString(this).toString()

var EditText.watcher: ((EditText, String) -> Unit)?
    get() = null
    set(value) {
        val self = this
        addTextChangedListener(object: EditorWatcher {
            override fun onTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int) {
                value?.invoke(self, var1?.toString()?: "")
            }
        })
    }
