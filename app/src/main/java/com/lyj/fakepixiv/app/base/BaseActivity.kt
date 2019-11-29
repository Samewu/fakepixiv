package com.lyj.fakepixiv.app.base

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistry
import com.lyj.fakepixiv.BR
import com.lyj.fakepixiv.R

/**
 * @author greensun
 *
 * @date 2019/3/16
 *
 * @desc
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel?> : AppCompatActivity() {
    protected lateinit var mBinding: V
    protected abstract val mViewModel: VM
    protected var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.let {
            lifecycle.addObserver(mViewModel as LifecycleObserver)
        }
        mBinding = DataBindingUtil.setContentView(this, bindLayout())
        mBinding.setVariable(bindViewModel(), mViewModel)
        mToolbar = mBinding.root.findViewById(bindToolbar())

    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    @LayoutRes
    abstract fun bindLayout() : Int

    open fun bindViewModel() : Int = BR.vm

    open fun bindToolbar() : Int = R.id.toolbar
}
