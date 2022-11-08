package com.trainee.appinventiv.eccoscreens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trainee.appinventiv.eccoscreens.databinding.ItemWalkThroughBinding

class TutorialScreenAdapter() : RecyclerView.Adapter<TutorialScreenAdapter.ViewHolder>() {

    private var imageList = ArrayList<Int>()

    fun setList(data : ArrayList<Int>){
        imageList = data
    }

    inner class ViewHolder(private val binding: ItemWalkThroughBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(image: Int) {
            binding.sivDummy.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWalkThroughBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}


/**
 * class TutorialScreenAdapter() : PagerAdapter() {

private val tutorialImageList = ArrayList<Int>()
override fun getCount(): Int {
return tutorialImageList.size
}

override fun isViewFromObject(view: View, `object`: Any): Boolean {
return view == `object`
}

override fun instantiateItem(container: ViewGroup, position: Int): Any {
return super.instantiateItem(container, position)
}

override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
super.destroyItem(container, position, `object`)
val vp = container as ViewPager
val view = `object` as View
vp.removeView(view)
}
}
 */