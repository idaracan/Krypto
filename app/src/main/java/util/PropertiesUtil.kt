package util

import android.content.Context
import java.util.*

abstract class PropertiesUtil{
    companion object {
        fun getProperties(file: String, context: Context): Properties{
            val properties = Properties()
            properties.load(context.assets.open(file))
            return properties
        }
    }

}