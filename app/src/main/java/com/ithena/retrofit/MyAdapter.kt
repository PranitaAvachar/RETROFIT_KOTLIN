package com.ithena.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context,val productlist:List<MyProductClassItem>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){

         var title:TextView
        var price:TextView
        var category:TextView
       // var rate:TextView
       // var count:TextView



        init {
            title=itemView.findViewById(R.id.title)
            price=itemView.findViewById(R.id.title)
            category=itemView.findViewById(R.id.category)
            //rate=itemView.findViewById(R.id.rate)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=productlist[position].title
        holder.price.text= productlist[position].price.toString()
        holder.category.text=productlist[position].category
       // holder.rate.text=productlist[position].rate
    }

    override fun getItemCount(): Int {
        return  productlist.size
    }
}

