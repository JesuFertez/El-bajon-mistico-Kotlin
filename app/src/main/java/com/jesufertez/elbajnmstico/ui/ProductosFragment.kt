package com.jesufertez.elbajnmstico.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jesufertez.elbajnmstico.data.ProductoRepository
import com.jesufertez.elbajnmstico.data.model.ProductosResponse
import com.jesufertez.elbajnmstico.data.remote.RetrofitClient
import com.jesufertez.elbajnmstico.databinding.FragmentProductosBinding
import com.jesufertez.elbajnmstico.presentation.ProductosViewModel
import com.jesufertez.elbajnmstico.presentation.ProductosViewModelFactory
import com.jesufertez.elbajnmstico.usecase.Calcular


class ProductosFragment : Fragment(),RecyclerClickInterface {

    private val productosApi = RetrofitClient.getProducto()
    private val productoRepository = ProductoRepository(productosApi)
    private val productoViewModelFactory = ProductosViewModelFactory(productoRepository)
    private val productosViewModel: ProductosViewModel by activityViewModels {
        productoViewModelFactory
    }

    private var rawBinding: FragmentProductosBinding? = null
    private val binding get() = rawBinding!!

    var total = 0
    var subtotal = 0
    var totalDescuento = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rawBinding = FragmentProductosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecycler()
        resetCampos()
    }

    private fun resetCampos() {
        binding.btnReset.setOnClickListener { view ->
            subtotal = 0
            totalDescuento = 0
            total = 0
            binding.tvSubTotalMonto.text = subtotal.toString()
            binding.tvTotalDescuentos.text = totalDescuento.toString()
            binding.tvTotalMonto.text = total.toString()
            setupViewModel()

        }
    }

    private fun setupViewModel() {
        productosViewModel.state().observe(this) {
            it?.let { safeProductosResponse ->
                handleUi(safeProductosResponse)
            }
        }
        productosViewModel.getProductos()
    }

    private fun handleUi(safeProductosResponse: ProductosResponse) {

        val adapter = ProductoAdapter(safeProductosResponse.products,this)
        binding.rvProductos.adapter = adapter
    }

    private fun setupRecycler() {
        binding.rvProductos.layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClick(position: Int, valor: Int, estado: Boolean) {

        if (!estado) {
            subtotal = subtotal + valor
            binding.tvSubTotalMonto.setText(subtotal.toString())

            totalDescuento = Calcular.opera(subtotal)

            binding.tvTotalDescuentos.setText(totalDescuento.toString())
            total = subtotal - totalDescuento
            binding.tvTotalMonto.setText(total.toString())
        }
        if (estado) {
            subtotal = subtotal - valor
            binding.tvSubTotalMonto.setText(subtotal.toString())
            totalDescuento = subtotal
            binding.tvTotalDescuentos.setText(totalDescuento.toString())
            total = subtotal
            binding.tvTotalMonto.setText(total.toString())
        }
    }
}