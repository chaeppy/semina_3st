package com.example.semina_3st.viewpager

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.semina_3st.InstaAdapter
import com.example.semina_3st.InstaData
import com.example.semina_3st.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    lateinit var instaAdapter: InstaAdapter
    val datas =  mutableListOf<InstaData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter = InstaAdapter(view.context)
        rv_home.adapter = instaAdapter //리사이클러뷰 어댑터를 insta Adapter로 지정
        loadDatas()
        rv_home.addItemDecoration(ItemDecoration())
    }

    private fun loadDatas(){
        datas.apply{
            add(
                InstaData(
                    userName = "김채원",
                    img_profile = "https://cdn.pixabay.com/photo/2020/04/19/08/17/watercolor-5062356__480.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2020/05/02/20/10/lonely-5122894__480.jpg"
                )
            )
            add(
                InstaData(
                    userName = "안드로이드",
                    img_profile = "https://cdn.pixabay.com/photo/2020/05/03/15/42/rose-5125534__480.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2020/05/04/14/14/cat-5129332__480.jpg"
                )
            )
            add(
                InstaData(
                    userName = "YB",
                    img_profile = "https://cdn.pixabay.com/photo/2020/04/20/20/17/lion-5069703__480.jpg",
                    img_contents = "https://cdn.pixabay.com/photo/2019/09/17/18/48/computer-4484282__480.jpg"
                )
            )
        }
        instaAdapter.datas = datas
        instaAdapter.notifyDataSetChanged()
    }

    class ItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
             //outRect.bottom = 20
        }
    }

}

