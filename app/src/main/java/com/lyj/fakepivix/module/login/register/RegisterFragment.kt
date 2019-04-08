package com.lyj.fakepivix.module.login.register

import android.os.Bundle
import com.lyj.fakepivix.R
import com.lyj.fakepivix.app.base.BackFragment
import com.lyj.fakepivix.databinding.FragmentRegisterBinding

/**
 * @author greensun
 *
 * @date 2019/3/20
 *
 * @desc
 */
class RegisterFragment : BackFragment<FragmentRegisterBinding, RegisterViewModel>() {

    companion object {
        fun newInstance(): RegisterFragment {
            val args = Bundle()
            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override var mViewModel: RegisterViewModel = RegisterViewModel()


    override fun init(savedInstanceState: Bundle?) {

    }

    override fun onKeyboardChanged(isOpen: Boolean, height: Int) {
        mViewModel.keyboardOpened.set(isOpen)
    }


    override fun bindLayout(): Int = R.layout.fragment_register

}