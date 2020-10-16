package com.fairy.module.dialog

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.fairy.module.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * 加载对话框
 *
 * @author: Fairy.
 * @date  : 2020/10/16.
 */
class LoadingDialog private constructor(
    private val mActivity: FragmentActivity?,
    private val minShowTime: Int,//最小显示时长
    private val minDelay: Int,//延迟时间
    private val style: Int//对话框样式
) :
    DialogFragment(),ILoadingDialog {

    private val loadTextKey = "loadingText"

    var mStartTime: Long = -1
    var mPostedHide = false
    var mPostedShow = false
    var mDismissed = false

    private val handler = Handler()

    private val mDelayedHide = Runnable {
        mPostedHide = false
        mStartTime = -1
        if (isAdded) {
            dismissAllowingStateLoss()
        }
    }

    private val mDelayedShow = Runnable {
        mPostedShow = false
        if (!mDismissed) {
            mStartTime = System.currentTimeMillis()
            mActivity?.supportFragmentManager?.let { show(it, this.javaClass.simpleName) }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, style)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.dialog_loading, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(loadTextKey).let {
            if (it != null) {
                load_tv.text = it
            }
        }
    }

    @Synchronized
    override fun hideDialog() {
        mDismissed = true
        handler.removeCallbacks(mDelayedShow)
        mPostedShow = false
        val diff = System.currentTimeMillis() - mStartTime
        if (diff >= minShowTime || mStartTime == -1L) {
            if (isAdded) {
                dismissAllowingStateLoss()
            }
        } else {
            if (!mPostedHide) {
                handler.postDelayed(mDelayedHide, minShowTime - diff)
                mPostedHide = true
            }
        }
    }

    @Synchronized
    override fun showDialog() {
        mStartTime = -1
        mDismissed = false
        handler.removeCallbacks(mDelayedHide)
        mPostedHide = false
        if (!mPostedShow) {
            handler.postDelayed(mDelayedShow, minDelay.toLong())
            mPostedShow = true
        }
    }

    override fun onPause() {
        super.onPause()
        this.removeCallbacks()
    }

    private fun removeCallbacks() {
        handler.removeCallbacks(mDelayedHide)
        handler.removeCallbacks(mDelayedShow)
    }

    companion object {

        fun builder(): FragmentBuilder = FragmentBuilder()

        class FragmentBuilder {

            /**
             * 最小显示时长
             */
            private var minShowTime: Int = 500

            /**
             *延迟时间
             */
            private var minDelay: Int = 500

            /**
             * 加载框样式
             */
            private var style: Int = R.style.Theme_AppCompat_Light_Dialog

            private var loadingText: String? = null

            private var args: Bundle = Bundle()

            fun build(
                activity: FragmentActivity?
            ): ILoadingDialog {
                val fragment = LoadingDialog(activity, minShowTime, minDelay, style)
                fragment.arguments = args
                args.putString(fragment.loadTextKey, loadingText)
                return fragment
            }

            /**
             * 最小显示时长
             * @param minShowTime 默认值：500
             */
            fun setMinShowTime(minShowTime: Int): FragmentBuilder {
                this.minShowTime = minShowTime
                return this
            }

            /**
             *延迟时间
             * @param minDelay 默认值：500
             */
            fun setMinDelay(minDelay: Int): FragmentBuilder {
                this.minDelay = minDelay
                return this
            }

            /**
             * 设置加载框样式
             * @param style 默认值：R.style.Theme_AppCompat_Light_Dialog
             */
            fun setStyle(style: Int): FragmentBuilder {
                this.style = style
                return this
            }

            /**
             * 设置加载文案
             * @param loadingText
             */
            fun setLoadingText(loadingText: String?): FragmentBuilder {
                this.loadingText = loadingText
                return this
            }
        }
    }
}