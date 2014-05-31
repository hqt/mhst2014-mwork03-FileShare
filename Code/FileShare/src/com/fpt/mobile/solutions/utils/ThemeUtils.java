package com.fpt.mobile.solutions.utils;

/**
 * Created by ThaoHQSE60963 on 5/30/14.
 */

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Provides static functions to more easily resolve attributes of the current theme
 */
public class ThemeUtils {
    /**
     * Resolves the given attribute id of the theme to a resource id
     */
    public static int getAttribute(Resources.Theme theme, int attrId) {
        final TypedValue outValue = new TypedValue();
        theme.resolveAttribute(attrId, outValue, true);
        return outValue.resourceId;
    }

    /**
     * Returns the resource id of the background used for buttons to show pressed and focused state
     */
    public static int getSelectableItemBackground(Resources.Theme theme) {
        return getAttribute(theme, android.R.attr.selectableItemBackground);
    }

    /**
     * Returns the resource id of the background used for list items that show activated background
     */
    public static int getActivatedBackground(Resources.Theme theme) {
        return getAttribute(theme, android.R.attr.activatedBackgroundIndicator);
    }
}
