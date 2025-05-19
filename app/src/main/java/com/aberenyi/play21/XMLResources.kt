/**
 * Course work for CIS-135. Final Project 2025 Spring.
 * Author: Antal Berenyi
 * Date: 2025-05-05
 */

package com.aberenyi.play21

import android.content.Context
import android.content.res.XmlResourceParser
import androidx.annotation.XmlRes
import org.xmlpull.v1.XmlPullParser

/**
 * Get the description belonging to the image with imageRes ID.
 * @param context Global information about an application environment
 * @param xmlResId The XML resource ID: XML resource reference.
 * @param searchKey The key to look up.
 * @return The description of the image, or "String is null" if not found.
 */
fun getDictionaryValueFromXml(context: Context, @XmlRes xmlResId: Int, searchKey: String): String {
    val parser: XmlResourceParser = context.resources.getXml(xmlResId)
    var key: String? = null
    var value: String? = null

    try {
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if (parser.name == "key") {
                        key = parser.nextText()
                    } else if (parser.name == "value") {
                        if (key == searchKey) {
                            value = parser.nextText()
                            break
                        }
                    }
                }
            }
            eventType = parser.next()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        parser.close()
    }
    //return searchKey
    return value ?: "String is null"
}