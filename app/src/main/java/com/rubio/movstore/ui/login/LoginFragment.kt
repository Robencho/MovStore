package com.rubio.movstore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rubio.movstore.R
import com.rubio.movstore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    //private val args: LoginFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeLiveData()
    }

    private fun setupListeners(){
        binding.btnLogin.setOnClickListener {
            validateLogin(
                binding.etUserName.text.toString(),
                binding.etUserPassword.text.toString()
            )
        }
    }
    private fun validateLogin(userName:String, password:String ){
        loginViewModel.initSession(userName, password)
    }
    private fun observeLiveData(){
        loginViewModel.stateLogin.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        })
    }
}