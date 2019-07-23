package com.lyj.fakepivix.module.main.news.follow

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyj.fakepivix.R
import com.lyj.fakepivix.app.App
import com.lyj.fakepivix.app.constant.Constant
import com.lyj.fakepivix.app.constant.IllustCategory
import com.lyj.fakepivix.app.constant.Restrict
import com.lyj.fakepivix.app.utils.SPUtil
import kotlinx.android.synthetic.main.dialog_restrict.*

/**
 * @author greensun
 *
 * @date 2019/5/29
 *
 * @desc
 */
class RestrictDialog : DialogFragment() {

    var onRestrict: ((restrict: String) -> Unit)? = null
    var category: String = IllustCategory.OTHER

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = View.inflate(context, R.layout.dialog_restrict, null)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val cache = when(category) {
            IllustCategory.NOVEL -> SPUtil.get(Constant.SP.KEY_RESTRICT_NOVEL, Restrict.PUBLIC)
            else -> SPUtil.get(Constant.SP.KEY_RESTRICT_ILLUST, Restrict.PUBLIC)
        }
        radioGroup.check(when(cache) {
            Restrict.ALL -> R.id.all
            Restrict.PUBLIC -> R.id.pub
            Restrict.PRIVATE -> R.id.pri
            else -> R.id.pub
        })
        confirm.setOnClickListener {
            val restrict = when (radioGroup.checkedRadioButtonId) {
                R.id.all -> Restrict.ALL
                R.id.pub -> Restrict.PUBLIC
                R.id.pri -> Restrict.PRIVATE
                else -> Restrict.PUBLIC
            }
            onRestrict?.invoke(restrict)
            when(category) {
                IllustCategory.NOVEL -> SPUtil.save(Constant.SP.KEY_RESTRICT_NOVEL, restrict)
                else -> SPUtil.save(Constant.SP.KEY_RESTRICT_ILLUST, restrict)
            }
            this.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog.setCancelable(true)
        val lp = dialog.window.attributes
        //lp.height = App.context.resources.displayMetrics.heightPixels/2
        lp.width = App.context.resources.displayMetrics.widthPixels*2/3
        dialog.window.attributes = lp
    }
}