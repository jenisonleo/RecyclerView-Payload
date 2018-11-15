package app.recyclerview.payload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewById = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = RecyclerAdapter()
        viewById.setAdapter(adapter)
        viewById.setLayoutManager(LinearLayoutManager(this))
        val callback = Helper(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(viewById)
    }
}
