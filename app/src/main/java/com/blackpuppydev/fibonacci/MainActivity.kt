package com.blackpuppydev.fibonacci


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_adapter.view.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var itemList:ArrayList<ItemFibonacci>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        list_item.layoutManager = LinearLayoutManager(this)
        loadView()

    }

    private fun loadView(){ //40index i[0]=0 i[1]=1

        val index0 = ItemFibonacci()
        index0.number = 0
        index0.status = "test0"

        val index1 = ItemFibonacci()
        index1.number = 1
        index1.status = "test1"

        itemList = arrayListOf(index0,index1)

        for (i in 2..40){
            val index = ItemFibonacci()
            index.number = (itemList[i-2].number + itemList[i-1].number)
            index.status = "test$i"

            Log.d("test index : " ,  "$i -> " + index.number + " , " + index.status)
            itemList.add(index)
        }

        list_item.adapter = FibonacciAdapter()




    }


    inner class FibonacciAdapter: RecyclerView.Adapter<ItemViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(parent)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.setData(itemList[position],position)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

    }


    inner class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_adapter, parent, false)), View.OnClickListener {

//        lateinit var title:TextView
//        lateinit var icon:ImageView


        init {
            list_item.setOnClickListener(this)
        }

        fun setData(item:ItemFibonacci,position: Int){
            itemView.title.text = "Index: $position, Number: ${item.number}"

            val images =
                intArrayOf(R.drawable.circle, R.drawable.close, R.drawable.square)
            val rand = Random()
            itemView.icon.setImageResource(images[rand.nextInt(images.size)])

        }

        override fun onClick(v: View?) {

            //show popup
        }


    }


    class ItemFibonacci{
        var number:Int = 0
        var status:String = ""
    }

}