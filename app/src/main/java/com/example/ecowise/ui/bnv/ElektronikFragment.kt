package com.example.ecowise.ui.bnv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.adapter.DeviceAdapter
import com.example.ecowise.adapter.ReportAdapter
import com.example.ecowise.databinding.FragmentElektronikBinding
import com.example.ecowise.ui.elektronik.DeviceActivity
import com.example.ecowise.ui.elektronik.EnergyActivity

class ElektronikFragment : Fragment() {

    private var _binding: FragmentElektronikBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv: RecyclerView
    private lateinit var rv_energy: RecyclerView

    private lateinit var reportAdapter: ReportAdapter
    private lateinit var deviceAdapter: DeviceAdapter

    private val viewModel: ElektronikViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentElektronikBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_energy_elektronik)
        rv_energy = view.findViewById(R.id.rv_device_elektronik)

        setUpObserver()
        prepareRecyclerView()

        binding.apply{
            tvAllDevice.setOnClickListener {
                navigateToAllDevice()
            }
            tvAllEnergy.setOnClickListener {
                navigateToEnergy()
            }
        }


    }

    private fun prepareRecyclerView() {
        reportAdapter = ReportAdapter()
        deviceAdapter = DeviceAdapter()
        reportAdapter.setOnItemClickCallback(object : ReportAdapter.OnItemClickListener {
            override fun onItemClick() {
            }
        })
        deviceAdapter.setOnItemClickCallback(object : DeviceAdapter.OnItemClickListener {
            override fun onItemClick() {
            }
        })
        rv.adapter = reportAdapter
        rv.layoutManager = LinearLayoutManager(context)

        rv_energy.adapter = deviceAdapter
        rv_energy.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

    }


    private fun setUpObserver() {
        viewModel.listReport.observe(viewLifecycleOwner) {
            reportAdapter.submitList(it)
        }
        viewModel.listDevice.observe(viewLifecycleOwner) {
            deviceAdapter.submitList(it)
        }
    }

    private fun navigateToEnergy() {
        val intent = Intent(getActivity(), EnergyActivity::class.java)
        getActivity()?.startActivity(intent)
    }

    private fun navigateToAllDevice() {
        val intent = Intent(getActivity(), DeviceActivity::class.java)
        getActivity()?.startActivity(intent)
    }


}