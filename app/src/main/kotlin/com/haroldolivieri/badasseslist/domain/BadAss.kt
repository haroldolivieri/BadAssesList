package com.haroldolivieri.badasseslist.domain

import java.io.Serializable
import java.util.*


data class BadAss(val id: String, val name: String?, val image: String?,
                  val birthday: Date?, val bio: String?) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (other is BadAss) {
            return this.id == other.id
        }

        return false
    }

    override fun hashCode(): Int = id.hashCode()
}