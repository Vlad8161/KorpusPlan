package org.dpiki.korpusplan

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: RouteItemAdapter
    private lateinit var mImage: ImageView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mTvFloor: TextView
    private lateinit var mFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mImage = findViewById(R.id.activity_main_image_view) as ImageView
        mRecyclerView = findViewById(R.id.activity_main_recycler) as RecyclerView
        mFab = findViewById(R.id.activity_main_fab) as FloatingActionButton
        mTvFloor = findViewById(R.id.activity_main_tv_floor) as TextView

        mAdapter = RouteItemAdapter(listOf(
                RouteItem(R.drawable.l_3a, "3a", 1),
                RouteItem(R.drawable.l_14a, "14a", 2),
                RouteItem(R.drawable.l_16, "16", 2),
                RouteItem(R.drawable.l_19, "19", 2),
                RouteItem(R.drawable.l_20, "20", 2),
                RouteItem(R.drawable.l_23, "23", 2),
                RouteItem(R.drawable.l_24, "24", 3),
                RouteItem(R.drawable.l_26, "26", 3),
                RouteItem(R.drawable.l_28, "28", 3),
                RouteItem(R.drawable.l_35, "35", 4),
                RouteItem(R.drawable.l_37, "37", 4),
                RouteItem(R.drawable.l_40, "40", 4)
        )) {
            mImage.setImageResource(it.resourceImage)
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            mTvFloor.text = "${it.floor} этаж"
        }

        mFab.setOnClickListener { mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED }

        mBottomSheetBehavior = BottomSheetBehavior.from(mRecyclerView)
        mBottomSheetBehavior.isHideable = true
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        mBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mFab.animate().translationY(0f)
                } else {
                    mFab.animate().translationY(500f)
                }
            }
        })

        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            mFab.animate().translationY(0f)
        } else {
            mFab.animate().translationY(500f)
        }

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mImage.setImageResource(mAdapter.data[0].resourceImage)
        mTvFloor.text = "1 этаж"
    }
}
