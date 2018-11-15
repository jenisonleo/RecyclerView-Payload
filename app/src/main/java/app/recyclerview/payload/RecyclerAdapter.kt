package app.recyclerview.payload

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import java.util.Random

class RecyclerAdapter : RecyclerView.Adapter<Holder>() {
    internal var data: IntArray
    internal var random = Random()

    init {
        data = IntArray(20)
        for (i in 0..19) {
            data[i] = i
        }
    }

    override fun onBindViewHolder(holder: Holder, i: Int,payloads: MutableList<Any>) {

        if(payloads.isEmpty()){
            fullBind(holder,i)
            dotask(data[i])
        }else if ((payloads[0] as DatType).equals(DatType.FULL)){
            fullBind(holder,i)
        }else if ((payloads[0] as DatType).equals(DatType.HALF)) {
            holder.textView.text = "partial " + data[i]
        }
    }

    fun fullBind(holder: Holder, i: Int){
        holder.textView.text = "" + data[i]
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Holder {
        val view = TextView(viewGroup.context)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300)
        view.setBackgroundColor(Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255)))
        view.layoutParams = params
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, i: Int) {
        notifyItemChanged(i, DatType.FULL)
    }

    fun dotask(x:Int){
        GlobalScope.launch {
            Thread.sleep(2000)
            GlobalScope.launch(Dispatchers.Main) {
                updateData(x)
            }.start()
        }.start()
    }

    fun updateData(j:Int){
        Log.e("update"," ")
        for (i in 0..(data.size-1)) {
            if( data[i] == j)
            {
                notifyItemChanged(i,DatType.HALF)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun swap(from: Int, to: Int) {
        val temp = data[from] // swaps n[3] and n[4]
        data[from] = data[to]
        data[to] = temp
        notifyItemMoved(from, to)
    }
}

enum class DatType{
    FULL,HALF
}