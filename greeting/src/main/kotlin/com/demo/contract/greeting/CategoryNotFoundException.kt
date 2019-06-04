package com.demo.contract.greeting

import java.lang.RuntimeException

class CategoryNotFoundException : RuntimeException {


	constructor(category: Int): super("Categoria não encontrada $category")

}
