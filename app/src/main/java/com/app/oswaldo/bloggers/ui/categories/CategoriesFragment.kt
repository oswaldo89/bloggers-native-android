package com.app.oswaldo.bloggers.ui.categories

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.oswaldo.bloggers.R
import com.app.oswaldo.bloggers.adapters.categories.ICardCategory
import com.app.oswaldo.bloggers.adapters.categories.SliderAdapter
import com.app.oswaldo.bloggers.adapters.receta.ICardReceta
import com.app.oswaldo.bloggers.adapters.receta.RecetaAdapter
import com.app.oswaldo.bloggers.databinding.FragmentCategoriesBinding
import com.app.oswaldo.bloggers.model.Category
import com.app.oswaldo.bloggers.model.Receta
import com.app.oswaldo.bloggers.ui.detail.DetailActivity
import com.ramotion.cardslider.CardSliderLayoutManager
import com.ramotion.cardslider.CardSnapHelper

@SuppressLint("SetTextI18n")
class CategoriesFragment : Fragment(), ICardCategory, ICardReceta {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var layoutManger: CardSliderLayoutManager
    private lateinit var sliderAdapter: SliderAdapter
    private var currentPosition = 0
    private val categoryList = ArrayList<Category>()

    private val mRecetasAdapter: RecetaAdapter = RecetaAdapter()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {

        _binding  = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root

        sliderAdapter = SliderAdapter()
        sliderAdapter.recyclerAdapter(dataModelCategory(), requireContext(), this)

        initRecyclerView()

        onActiveCardChange(0)
        return view
    }

    private fun dataModelCategory(): ArrayList<Category> {
        categoryList.add(Category(1,"Carnita asada", 152, "https://dam.cocinafacil.com.mx/wp-content/uploads/2019/06/parrillada2.jpg"))
        categoryList.add(Category(2,"Postres", 36, "https://www.hola.com/imagenes/cocina/recetas/20190806147086/receta-batido-galletas-oreo-vainilla/0-707-944/recetas-postres-faciles-galletas-oreo-m.jpg"))
        categoryList.add(Category(3,"Comida Mexicana", 17, "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/06/curiosidades-de-la-comida-mexicana-que-no-conocias.jpg"))
        return categoryList
    }

    private fun dataModel(): ArrayList<Receta> {
        val lista = ArrayList<Receta>()
        lista.add(Receta(1,"Carnita asada", "La carnita asada es un platillo tipico del Norte",3276,54 , "https://dam.cocinafacil.com.mx/wp-content/uploads/2019/06/parrillada2.jpg"))
        lista.add(Receta(2,"Carnita asada", "La carnita asada es un platillo tipico del Norte",3276,54 , "https://saboryestilo.com.mx/wp-content/uploads/2020/01/tips-para-hacer-la-mejor-carne-asada-1200x720.jpg"))
        lista.add(Receta(3,"Carnita asada", "La carnita asada es un platillo tipico del Norte",3276,54 , "https://d1e3z2jco40k3v.cloudfront.net/-/media/mccormick-us/recipes/lawrys/c/2000/carne_asada_steak2000x1125.jpg"))
        lista.add(Receta(4,"Carnita asada", "La carnita asada es un platillo tipico del Norte",3276,54 , "https://www.cocogrill.net/wp-content/uploads/2019/05/carne-asada-ala-tampique%C3%B1a-cocogrill.jpg"))
        lista.add(Receta(5,"Carnita asada", "La carnita asada es un platillo tipico del Norte",3276,54 , "https://i.ytimg.com/vi/IFCsoNGs0A8/maxresdefault.jpg"))
        return lista
    }

    private fun initRecyclerRecetas(){
        binding.recyclerRecetas.setHasFixedSize(true)
        binding.recyclerRecetas.layoutManager = LinearLayoutManager(requireActivity())

        mRecetasAdapter.recyclerAdapter(dataModel(), requireContext(), this)
        binding.recyclerRecetas.adapter = mRecetasAdapter
    }

    private fun initRecyclerView() {
        binding.recyclerCategories.adapter = sliderAdapter
        binding.recyclerCategories.setHasFixedSize(true)
        binding.recyclerCategories.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerCategories: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange()
                }
            }
        })
        layoutManger = binding.recyclerCategories.layoutManager as CardSliderLayoutManager
        CardSnapHelper().attachToRecyclerView(binding.recyclerCategories)
    }

    private fun onActiveCardChange() {
        val pos = layoutManger.activeCardPosition
        onActiveCardChange(pos)
    }

    private fun onActiveCardChange(pos: Int) {
        currentPosition = pos
        binding.tsTemperature.text = "${categoryList[currentPosition].totalItems} recetas!"
        binding.txtCategory.text = categoryList[currentPosition].description

        initRecyclerRecetas()
    }

    override fun onCategoryTouch(item: Category, position: Int) {
        binding.recyclerCategories.smoothScrollToPosition(position)
        onActiveCardChange(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding  = null
    }

    override fun onItemTouch(item: Receta) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        startActivity(intent)
    }

}