package com.learning.mysample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 03.07.2017.
 */
public class ColorThemes {
    public  Map<Integer,Integer[]> mIdThemeToColorMap;
    public  Map<Integer,Integer> mColorMap;

  public ColorThemes(){
            mColorMap = new HashMap();

            mColorMap.put(R.style.NewTheme_purple_theme,R.color.purple);
            mColorMap.put(R.style.NewTheme_orange_theme, R.color.orange );
            mColorMap.put(R.style.NewTheme_blue_theme,R.color.blue);
            mColorMap.put(R.style.NewTheme_green_theme,R.color.green);
            mColorMap.put(R.style.NewTheme_pink_theme,R.color.pink);
            mColorMap.put(R.style.NewTheme_white_theme,R.color.white);

            mIdThemeToColorMap = new HashMap();

            mIdThemeToColorMap.put(R.id.lime_theme,new Integer[]{R.color.lime,R.style.NewTheme_lime_theme});
            mIdThemeToColorMap.put(R.id.orange_theme,new Integer[]{R.color.orange, R.style.NewTheme_orange_theme});
            mIdThemeToColorMap.put(R.id.blue_theme,new Integer[]{R.color.blue,R.style.NewTheme_blue_theme});
            mIdThemeToColorMap.put(R.id.green_theme,new Integer[]{R.color.green,R.style.NewTheme_green_theme});
            mIdThemeToColorMap.put(R.id.pink_theme,new Integer[]{R.color.pink,R.style.NewTheme_pink_theme});
            mIdThemeToColorMap.put(R.id.white_theme,new Integer[]{R.color.white,R.style.NewTheme_white_theme});

        }



}

