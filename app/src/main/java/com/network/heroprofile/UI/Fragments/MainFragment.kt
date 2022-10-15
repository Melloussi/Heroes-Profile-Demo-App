package com.network.heroprofile.UI.Fragments


import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.network.heroprofile.MODEL.DATA.DataClases.Hero
import com.network.heroprofile.R
import com.network.heroprofile.UI.Adapter.HerosAdapter
import com.network.heroprofile.ViewModel.HeroesViewModel


class MainFragment : Fragment() {

    private val heroesVM: HeroesViewModel by viewModels()
    //viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val context = requireContext()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val progress = view.findViewById<ProgressBar>(R.id.progress)
        val CenterProgress = view.findViewById<ProgressBar>(R.id.CenterProgress)


        heroesVM.getHeroes(0)
        heroesVM.getAll.observe(viewLifecycleOwner, Observer { list ->
            var adapter: HerosAdapter? = null
            val state = heroesVM.state

            if (list.isNotEmpty()){
                CenterProgress.visibility = View.GONE
                progress.visibility = View.GONE
                adapter = HerosAdapter(context, list)
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = adapter
                if (state != null){
                    recyclerView.layoutManager?.onRestoreInstanceState(state)
                }

                var loadNewData = false
                recyclerView.addOnScrollListener( object : RecyclerView.OnScrollListener(){

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        if(!heroesVM.reachEnd){

                            val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)!!
                            val totalItemCount = layoutManager.itemCount
                            val lastVisible = layoutManager.findLastVisibleItemPosition()+1

                            if (lastVisible == totalItemCount && !loadNewData) {
                                //println("------> Bottom")
                                progress.visibility = View.VISIBLE
                                heroesVM.state = recyclerView.layoutManager!!.onSaveInstanceState()
                                val page = heroesVM.getPage()
                                println("------ Page[$page] _List[$totalItemCount] totalItemCount[$totalItemCount] lastVisible[$lastVisible]")
                                heroesVM.getHeroes(page)
                                loadNewData = true
                            }else if((lastVisible+1)*2 < totalItemCount && loadNewData){
                                //println("------> Not Reached the Bottom Yet")
                                loadNewData = false
                            }
                        }else{
                            progress.visibility = View.GONE
                        }
                    }
                })
            }
        })







        return view
    }
}