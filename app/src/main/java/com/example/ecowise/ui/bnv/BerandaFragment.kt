package com.example.ecowise.ui.bnv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.adapter.DeviceAdapter
import com.example.ecowise.model.Device
import com.google.firebase.firestore.FirebaseFirestore


class BerandaFragment : Fragment() {

    private lateinit var rv: RecyclerView

    private lateinit var deviceAdapter: DeviceAdapter


    private val viewModel: BerandaViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_device_beranda)

        setUpObserver()
        prepareRecyclerView()

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