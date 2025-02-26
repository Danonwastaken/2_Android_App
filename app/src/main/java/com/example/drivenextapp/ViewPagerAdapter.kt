package com.example.drivenextapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.drivenextapp.databinding.ActivityOnboardingBinding
import com.google.android.material.button.MaterialButton

class ViewPagerAdapter(private val viewPager: ViewPager2): RecyclerView.Adapter<PagerVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =

        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.activity_onboarding, parent, false))


    override fun getItemCount(): Int = 3


    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        val tvCarOb = this.findViewById<TextView>(R.id.tvCarOb)
        val tvCarDescOb = this.findViewById<TextView>(R.id.tvCarDescOb)
        val mbNextOb = this.findViewById<MaterialButton>(R.id.mbNextOb)
        val ivMainOb = this.findViewById<ImageView>(R.id.ivMainOb)
        val mbSkipOb = this.findViewById<MaterialButton>(R.id.mbSkipOb)
        val ivFirstSwapOb = this.findViewById<ImageView>(R.id.ivFirstSwapOb)
        val ivSecondSwapOb = this.findViewById<ImageView>(R.id.ivSecondSwapOb)
        val ivThirdSwapOb = this.findViewById<ImageView>(R.id.ivThirdSwapOb)
        when(position) {
            0 -> {
                tvCarOb.text = context.resources.getString(R.string.desc_carrental)
                tvCarDescOb.text = context.resources.getString(R.string.desc_onboarding1)
                mbNextOb.text = context.resources.getString(R.string.button_next)
                ivMainOb.setImageResource(R.drawable.ic_onboarding1)
                ivFirstSwapOb.setImageResource(R.drawable.ic_purple_line)
                ivSecondSwapOb.setImageResource(R.drawable.ic_gray_line)
                ivThirdSwapOb.setImageResource(R.drawable.ic_gray_line)
            }

            1 -> {
                tvCarOb.text = context.resources.getString(R.string.desc_safeandcomf)
                tvCarDescOb.text = context.resources.getString(R.string.desc_onboarding2)
                mbNextOb.text = context.resources.getString(R.string.button_next)
                ivMainOb.setImageResource(R.drawable.ic_onboarding2)
                ivFirstSwapOb.setImageResource(R.drawable.ic_gray_line)
                ivSecondSwapOb.setImageResource(R.drawable.ic_purple_line)
                ivThirdSwapOb.setImageResource(R.drawable.ic_gray_line)
            }

            2 -> {
                tvCarOb.text = context.resources.getString(R.string.desc_thebestoffers)
                tvCarDescOb.text = context.resources.getString(R.string.desc_onboarding3)
                mbNextOb.text = context.resources.getString(R.string.button_nextt)
                ivMainOb.setImageResource(R.drawable.ic_onboarding3)
                ivFirstSwapOb.setImageResource(R.drawable.ic_gray_line)
                ivSecondSwapOb.setImageResource(R.drawable.ic_gray_line)
                ivThirdSwapOb.setImageResource(R.drawable.ic_purple_line)
            }
        }
            mbNextOb.setOnClickListener {
                if (mbNextOb.text  == context.resources.getString(R.string.button_nextt)) {
                    viewPager.visibility = View.GONE
                }
                else {
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                }
        }
            mbSkipOb.setOnClickListener {
                viewPager.visibility = View.GONE
            }
    }

}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)