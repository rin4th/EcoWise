package com.example.ecowise.ui.elektronik

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.adapter.DeviceAdapter
import com.example.ecowise.adapter.ReportAdapter
import com.example.ecowise.databinding.FragmentDeviceBinding
import com.example.ecowise.databinding.FragmentElektronikBinding
import com.example.ecowise.ui.bnv.ElektronikViewModel
import com.example.ecowise.ui.bnv.HomeActivity

class DeviceFragment : Fragment() {

    private var _binding: FragmentDeviceBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv: RecyclerView

    private lateinit var deviceAdapter: DeviceAdapter

    private val viewModel: DeviceViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDeviceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_device)

        setUpObserver()
        prepareRecyclerView()

        binding.apply {
            toolbar.setNavigationOnClickListener {
                navigateToHome()
            }
        }

    }

    private fun navigateToHome() {
        val intent = Intent(getActivity(), HomeActivity::class.java)
        getActivity()?.startActivity(intent)
    }

    private fun prepareRecyclerView() {
        deviceAdapter = DeviceAdapter()

        deviceAdapter.setOnItemClickCallback(object : DeviceAdapter.OnItemClickListener {
            override fun onItemClick() {
            }
        })
        rv.adapter = deviceAdapter
        rv.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpObserver() {

        viewModel.listDevice.observe(viewLifecycleOwner) {
            deviceAdapter.submitList(it)
        }
    }
}