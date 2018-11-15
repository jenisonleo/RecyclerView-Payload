package app.recyclerview.payload

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView

    init {
        textView = itemView as TextView
        textView.gravity = Gravity.CENTER
    }
}
