package com.app.oswaldo.bloggers.adapters.categories

import com.app.oswaldo.bloggers.model.Category
import com.app.oswaldo.bloggers.model.Receta

interface ICardCategory {
    fun onCategoryTouch(item: Category, position: Int)
}