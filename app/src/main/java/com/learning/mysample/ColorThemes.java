package com.learning.mysample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Диана on 03.07.2017.
 */
public class ColorThemes {

    public  Map<Integer,Integer> mColorMap;

  public ColorThemes(){
            mColorMap = new HashMap();

            mColorMap.put(R.id.lime_theme,R.color.lime);
            mColorMap.put(R.id.orange_theme, R.color.orange );
            mColorMap.put(R.id.blue_theme,R.color.blue);
            mColorMap.put(R.id.green_theme,R.color.green);
            mColorMap.put(R.id.pink_theme,R.color.pink);
            mColorMap.put(R.id.white_theme,R.color.white);

        }



}

