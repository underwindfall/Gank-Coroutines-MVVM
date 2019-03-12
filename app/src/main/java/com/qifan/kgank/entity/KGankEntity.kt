package com.qifan.kgank.entity

/**
 * Created by Qifan on 11/02/2019.
 */
data class KGankEntity(
    val error: Boolean? = null,
    val results: List<KGankResultsItem?>? = null
)

data class KGankResultsItem(
    val createdAt: String? = null,
    val publishedAt: String? = null,
    val id: String? = null,
    val source: String? = null,
    val used: Boolean? = null,
    val type: String? = null,
    val url: String? = null,
    val desc: String? = null,
    val who: String? = null,
    val images: List<String?>? = null
)