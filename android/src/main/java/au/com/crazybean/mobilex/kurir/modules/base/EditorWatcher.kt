package au.com.crazybean.mobilex.kurir.modules.base

import android.text.Editable
import android.text.TextWatcher

interface EditorWatcher : TextWatcher {
    override fun afterTextChanged(var1: Editable?) {
    }

    override fun beforeTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int) {
    }

    override fun onTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int)
}