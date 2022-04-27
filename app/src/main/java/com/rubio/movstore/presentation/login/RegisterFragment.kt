package com.rubio.movstore.presentation.login

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
import com.rubio.movstore.data.models.UserParcelable
import com.rubio.movstore.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val loginViewModel: LoginViewModel by activityViewModels()
    //private val args: LoginFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeLiveData()
    }

    private fun setupListeners() {
        binding.btnSaveUser.setOnClickListener {
            buildNewUser()
        }
    }

    private fun buildNewUser() {
        var user: UserParcelable? = null
        user = UserParcelable(
            0,
            binding.etUserName.text.toString(),
            binding.etUserDocument.text.toString().toInt(),
            binding.etUserEmail.text.toString(),
            binding.etUserPassWord.text.toString(),
            false
        )
        loginViewModel.insertNewUser(user, requireActivity())
    }

    private fun observeLiveData() {
        loginViewModel.validateSessionInit.observe(viewLifecycleOwner, Observer {
            if (!it) {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        })
    }
}