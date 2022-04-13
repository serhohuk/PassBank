package com.serhohuk.passbank.adapters

import android.app.KeyguardManager
import android.content.Context
import android.view.*
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.serhohuk.passbank.R


const val FINGERPRINT_ITEM = 100
const val BACKSPACE_ITEM = 101

class NumPadAdapter(val mContext : Context) : BaseAdapter() {

    private val items = mutableListOf<Int>()

    init {
        items.addAll(1..9)
        items.add(FINGERPRINT_ITEM)
        items.add(0)
        items.add(BACKSPACE_ITEM)
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewItem : View? = if (convertView==null){
            val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.item_numpad,parent,false)
        } else{
            convertView
        }

        val imageView = viewItem?.findViewById<ImageView>(R.id.iv_pad)
        val textView = viewItem?.findViewById<TextView>(R.id.tv_pad)
        val frameLayout = viewItem?.findViewById<FrameLayout>(R.id.fl_pad)

        val display: Display = (mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        frameLayout?.layoutParams = FrameLayout.LayoutParams(display.width/4,display.width/4)

        if (position == 9){
            textView?.visibility = View.GONE
            imageView?.visibility = View.VISIBLE
            imageView?.setImageResource(R.drawable.ic_fingerprint)
            val keyGuardManager = mContext.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            if(!keyGuardManager.isKeyguardSecure) imageView?.visibility = View.GONE
        } else if (position == 11){
            textView?.visibility = View.GONE
            imageView?.visibility = View.VISIBLE
            imageView?.setImageResource(R.drawable.ic_backspace)
        } else {
            textView?.visibility = View.VISIBLE
            imageView?.visibility = View.GONE
            textView?.text = items[position].toString()
        }
        return viewItem!!
    }
}