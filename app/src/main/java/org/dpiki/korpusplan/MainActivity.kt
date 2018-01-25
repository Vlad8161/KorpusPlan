package org.dpiki.korpusplan

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: RouteItemAdapter
    private lateinit var mImage: ImageView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var mFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mImage = findViewById(R.id.activity_main_image_view) as ImageView
        mRecyclerView = findViewById(R.id.activity_main_recycler) as RecyclerView
        mFab = findViewById(R.id.activity_main_fab) as FloatingActionButton

        mAdapter = RouteItemAdapter(listOf(
                RouteItem(R.drawable.l_3a, "3a"),
                RouteItem(R.drawable.l_14a, "14a"),
                RouteItem(R.drawable.l_16, "16"),
                RouteItem(R.drawable.l_19, "19"),
                RouteItem(R.drawable.l_20, "20"),
                RouteItem(R.drawable.l_23, "23"),
                RouteItem(R.drawable.l_24, "24"),
                RouteItem(R.drawable.l_26, "26"),
                RouteItem(R.drawable.l_28, "28"),
                RouteItem(R.drawable.l_35, "35"),
                RouteItem(R.drawable.l_37, "37"),
                RouteItem(R.drawable.l_40, "40")
        )) {
            mImage.setImageResource(it.resourceImage)
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
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
    }
}
