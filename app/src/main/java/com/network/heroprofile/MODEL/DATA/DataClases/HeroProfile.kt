package com.network.heroprofile.MODEL.DATA.DataClases

data class HeroProfile(
    val _id: String,
    val appearance: Appearance,
    val biography: Biography,
    val connections: Connections,
    val id: Int,
    val images: Images,
    val name: String,
    val powerstats: Powerstats,
    val slug: String,
    val work: Work
)