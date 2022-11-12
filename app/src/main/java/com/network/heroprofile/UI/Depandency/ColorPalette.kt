package com.network.heroprofile.UI.Depandency

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.palette.graphics.Palette


class ColorPalette(val result: (result:ArrayList<Int>) -> Unit) {
    private var vibrantSwatch: Palette.Swatch? = null
    private var lightVibrantSwatch: Palette.Swatch? = null
    private var darkVibrantSwatch: Palette.Swatch? = null
    private var mutedSwatch: Palette.Swatch? = null
    private var lightMutedSwatch: Palette.Swatch? = null
    private var darkMutedSwatch: Palette.Swatch? = null

    private val swatchNumber = 0

    fun generateColor(image:ImageView){
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val list = ArrayList<Int>()

        Palette.from(bitmap).generate { palette ->
            vibrantSwatch = palette!!.vibrantSwatch
            lightVibrantSwatch = palette.lightVibrantSwatch
            darkVibrantSwatch = palette.darkVibrantSwatch
            mutedSwatch = palette.mutedSwatch
            lightMutedSwatch = palette.lightMutedSwatch
            darkMutedSwatch = palette.darkMutedSwatch

            list.add(vibrantSwatch!!.rgb)
            list.add(lightVibrantSwatch!!.rgb)
            list.add(darkVibrantSwatch!!.rgb)
            list.add(mutedSwatch!!.rgb)
            list.add(lightMutedSwatch!!.rgb)
            list.add(darkMutedSwatch!!.rgb)



            result(list)
        }

        if(lightVibrantSwatch != null){
            println("Result: ${darkVibrantSwatch!!.rgb}")
        }else{
            println("No Result :/")
        }
    }
}