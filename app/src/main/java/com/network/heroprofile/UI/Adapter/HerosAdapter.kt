package com.network.heroprofile.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.network.heroprofile.MODEL.DATA.DataClases.Hero
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.R
import com.network.heroprofile.UI.Depandency.ColorPalette
import com.squareup.picasso.Picasso

class HerosAdapter(private val context: Context, private val list: MutableList<HeroProfile>, val itemClicked: (item:HeroProfile) -> Unit) :  RecyclerView.Adapter<HerosAdapter.MyViewHolder>(){

//    init {
//        val localList : MutableList<HeroProfile>
//
//    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
         val leftImage: ImageView = view.findViewById<ImageView>(R.id.image)
        val text: TextView = view.findViewById<TextView>(R.id.tvLeft)
        val face: View = view.findViewById<View>(R.id.face)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_model, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(list[position].images.sm).into(holder.leftImage)
        holder.text.text = list[position].name

        holder.face.setOnClickListener(View.OnClickListener {
            itemClicked(list[position])
        })


    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun addMoreData(newDataList: MutableList<HeroProfile>){
        list.addAll(newDataList)
        notifyDataSetChanged()
    }
}