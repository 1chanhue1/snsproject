package com.akbkbaaa.snsproject
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class ViewPager(private val context: Context, private val photos: List<Int>) : PagerAdapter() {

    override fun getCount(): Int {
        return photos.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_image_slide, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(photos[position])
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
