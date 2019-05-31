package com.demo.contract.coolgreeting

import javax.validation.constraints.NotEmpty

data class Person(@field:NotEmpty val name : String) {
}