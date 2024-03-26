package com.example.ecowise.ui.bnv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecowise.model.Product
import com.google.firebase.firestore.FirebaseFirestore

class MerchantViewModel : ViewModel(){

    private val _listProduct = MutableLiveData<ArrayList<Product>>()

    val listProduct: LiveData<ArrayList<Product>>
        get() = _listProduct

    init {
        _listProduct.value = ArrayList()
        prepareResource()
    }

    private fun prepareResource() {
        val db = FirebaseFirestore.getInstance()
        val productRef = db.collection("product")

        productRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val listItem = arrayListOf<Product>()
                for (document in task.result!!) {
                    val productData = document.data!!
                    val product = Product(
                        id = document.id,
                        name = productData["name"] as String,
                        icon = productData["icon"] as String,
                        off = (productData["off"] as Long).toInt(),
                        price = (productData["price"] as Long).toInt()
                    )
                    listItem.add(product)
                }
                _listProduct.value = listItem

            }
        }
    }
    
}