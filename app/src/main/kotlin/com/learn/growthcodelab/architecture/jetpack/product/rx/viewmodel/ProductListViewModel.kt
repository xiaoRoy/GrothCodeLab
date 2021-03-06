package com.learn.growthcodelab.architecture.jetpack.product.rx.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.growthcodelab.architecture.jetpack.product.model.Product
import com.learn.growthcodelab.architecture.jetpack.product.rx.data.ProductDataSource
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class ProductListViewModel(private val productDataSource: ProductDataSource) : ViewModel() {

    val allProducts = MutableLiveData<List<Product>>()

    val productsWithDescription = MutableLiveData<List<Product>>()


    fun loadAllProducts() {
        productDataSource
                .loadAllProducts().subscribe(object : SingleObserver<List<Product>> {
                    override fun onSuccess(productList: List<Product>) {
                        allProducts.value = productList
                    }

                    override fun onSubscribe(disposable: Disposable) {
                    }

                    override fun onError(exception: Throwable) {
                    }
                }
                )
    }

    fun loadProductsWithDescription() {
        productDataSource
                .loadAllProducts().subscribe(object : SingleObserver<List<Product>> {
                    override fun onSuccess(productList: List<Product>) {
                        productsWithDescription.value = productList.filter { it.description.isNotBlank() }
                    }

                    override fun onSubscribe(disposable: Disposable) {
                    }

                    override fun onError(exception: Throwable) {
                    }
                }
                )
    }
}