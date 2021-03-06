package com.learn.growthcodelab.architecture.jetpack.product.live.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.learn.growthcodelab.architecture.jetpack.product.ProductTestFactory
import com.learn.growthcodelab.architecture.jetpack.product.live.data.ProductDataSource
import com.learn.growthcodelab.architecture.jetpack.product.model.Product
import com.learn.growthcodelab.capture
import com.learn.growthcodelab.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

class ProductListViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var productDataSource: ProductDataSource

    @Captor
    private lateinit var productsCaptor: ArgumentCaptor<List<Product>>

    private lateinit var productListViewModel: ProductListViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        productListViewModel = ProductListViewModel(productDataSource)
    }

    @Test
    fun test_loadAllProducts_empty() {
        val allProducts = MutableLiveData<List<Product>>()
        allProducts.value = emptyList()
        Mockito.`when`(productDataSource.loadAllProducts()).thenReturn(allProducts)
        val observer = mock<Observer<List<Product>>>()
        productListViewModel.loadAllProducts().observeForever(observer)
        Mockito.verify(observer).onChanged(capture(productsCaptor))
        Assert.assertTrue(productsCaptor.value.isEmpty())
    }

    @Test
    fun test_loadAllProducts() {
        val allProducts = MutableLiveData<List<Product>>()
        allProducts.value = ProductTestFactory.createProducts()
        Mockito.`when`(productDataSource.loadAllProducts()).thenReturn(allProducts)
        val observer = mock<Observer<List<Product>>>()
        productListViewModel.loadAllProducts().observeForever(observer)
        Mockito.verify(observer).onChanged(capture(productsCaptor))
        Assert.assertEquals(3, productsCaptor.value.size)
    }

    @Test
    fun tst_loadProductsWithDescription() {
        val allProducts = MutableLiveData<List<Product>>()
        allProducts.value = ProductTestFactory.createProducts()
        Mockito.`when`(productDataSource.loadAllProducts()).thenReturn(allProducts)
        val observer = mock<Observer<List<Product>>>()
        productListViewModel.loadProductWithDescription().observeForever(observer)
        Mockito.verify(observer).onChanged(capture(productsCaptor))
        Assert.assertEquals(2, productsCaptor.value.size)
    }
}