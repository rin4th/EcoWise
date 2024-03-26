package com.example.ecowise.ui.bnv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecowise.R
import com.example.ecowise.adapter.DeviceAdapter
import com.example.ecowise.adapter.ProductAdapter
import com.example.ecowise.databinding.FragmentDeviceBinding
import com.example.ecowise.databinding.FragmentMerchantBinding
import com.example.ecowise.ui.elektronik.DeviceViewModel


class MerchantFragment : Fragment() {

    private var _binding: FragmentMerchantBinding? = null
    private val binding get() = _binding!!

    private lateinit var rv: RecyclerView

    private lateinit var productAdapter: ProductAdapter

    private val viewModel: MerchantViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMerchantBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = view.findViewById(R.id.rv_product)

        setUpObserver()
        prepareRecyclerView()
    }

    private fun prepareRecyclerView() {
        productAdapter = ProductAdapter()

        productAdapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickListener {
            override fun onItemClick() {
            }
        })
        rv.adapter = productAdapter
        rv.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpObserver() {
        viewModel.listProduct.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
        }
    }
}