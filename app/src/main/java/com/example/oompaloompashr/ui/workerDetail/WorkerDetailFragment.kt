package com.example.oompaloompashr.ui.workerlists.workerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.oompaloompashr.Injection
import com.example.oompaloompashr.databinding.FragmentWrokerDetailBinding
import com.example.oompaloompashr.utils.TextUtils
import kotlinx.coroutines.launch

class WorkerDetailFragment : Fragment() {

    private lateinit var viewModel: WorkerDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentWrokerDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this, Injection.provideWorkersDetailViewModelFactory(
                context = requireContext().applicationContext,
                owner = this
            )
        )
                .get(WorkerDetailViewModel::class.java)

        getWorkerDetail(viewModel, savedInstanceState)
        observers(viewModel, binding)
            return binding.root

    }

    private fun observers(viewModel: WorkerDetailViewModel, binding: FragmentWrokerDetailBinding) {
        viewModel.worker.observe(viewLifecycleOwner, Observer {
            it.let {
                Glide.with(this).load(it.image).into(binding.image)
                binding.firstNameText.text = it?.firstName
                binding.lastNameText.text = it?.lastName
                binding.ageText.text = it?.age.toString()
                binding.countryText.text = it?.country
                binding.emailText.text = it?.email
                binding.professisonText.text = it?.profession
                binding.favoriteText.text = it?.favorite?.food
                binding.genderText.text = it?.gender
                binding.quotaText.text = it?.quota
                binding.heightText.text = TextUtils.getHeightText(it?.height)
                binding.descriptionText.text = it?.description

            }
        })
    }

    private fun getWorkerDetail(viewModel: WorkerDetailViewModel, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            val id : Long = WorkerDetailFragmentArgs.fromBundle(requireArguments()).arg
            viewModel.getWorkerDetail(id)



        }
    }


}