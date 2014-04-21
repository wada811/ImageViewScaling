/*
 * Copyright 2014 wada811<at.wada811@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.wada811.imageviewscaling;

import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView.ScaleType;
import java.util.ArrayList;

public class Param {

    public static enum ParamName {

        /**
         * 
         */
        scaleType(R.string.scaleType),
        /**
         * 
         */
        layout_width(R.string.layout_width),
        /**
         * 
         */
        layout_height(R.string.layout_height),
        /**
         * 
         */
        adjustViewBounds(R.string.adjustViewBounds);

        public int labelId;

        private <E> ParamName(int labelId) {
            this.labelId = labelId;
        }
    }

    public static ArrayList<Integer> getValues(ParamName paramName){
        ArrayList<Integer> values = new ArrayList<Integer>();
        switch(paramName){
            case scaleType:
                values.add(R.string.scaleType_matrix);
                values.add(R.string.scaleType_fitXY);
                values.add(R.string.scaleType_fitCenter);
                values.add(R.string.scaleType_fitStart);
                values.add(R.string.scaleType_fitEnd);
                values.add(R.string.scaleType_center);
                values.add(R.string.scaleType_centerCrop);
                values.add(R.string.scaleType_centerInside);
                break;
            case layout_width:
                values.add(R.string.layout_match_parent);
                values.add(R.string.layout_wrap_content);
                break;
            case layout_height:
                values.add(R.string.layout_match_parent);
                values.add(R.string.layout_wrap_content);
                break;
            case adjustViewBounds:
                values.add(R.string.adjustViewBounds_true);
                values.add(R.string.adjustViewBounds_false);
                break;
            default:
                break;
        }
        return values;
    }

    public static ScaleType getScaleType(int scaleTypeId){
        switch(scaleTypeId){
            case R.string.scaleType_matrix:
                return ScaleType.MATRIX;
            case R.string.scaleType_fitXY:
                return ScaleType.FIT_XY;
            case R.string.scaleType_fitCenter:
                return ScaleType.FIT_CENTER;
            case R.string.scaleType_fitStart:
                return ScaleType.FIT_START;
            case R.string.scaleType_fitEnd:
                return ScaleType.FIT_END;
            case R.string.scaleType_center:
                return ScaleType.CENTER;
            case R.string.scaleType_centerCrop:
                return ScaleType.CENTER_CROP;
            case R.string.scaleType_centerInside:
                return ScaleType.CENTER_INSIDE;
            default:
                return ScaleType.MATRIX;
        }
    }

    public static int getScaleTypeName(ScaleType scaleType){
        switch(scaleType){
            case MATRIX:
                return R.string.scaleType_matrix;
            case FIT_XY:
                return R.string.scaleType_fitXY;
            case FIT_CENTER:
                return R.string.scaleType_fitCenter;
            case FIT_START:
                return R.string.scaleType_fitStart;
            case FIT_END:
                return R.string.scaleType_fitEnd;
            case CENTER:
                return R.string.scaleType_center;
            case CENTER_CROP:
                return R.string.scaleType_centerCrop;
            case CENTER_INSIDE:
                return R.string.scaleType_centerInside;
            default:
                return 0;
        }
    }

    public static int getLayoutParams(int layout){
        switch(layout){
            case R.string.layout_wrap_content:
                return LayoutParams.WRAP_CONTENT;
            case R.string.layout_match_parent:
                return LayoutParams.MATCH_PARENT;
            default:
                return 0;
        }
    }

    public static int getLayoutParamsName(int layout){
        switch(layout){
            case LayoutParams.WRAP_CONTENT:
                return R.string.layout_wrap_content;
            case LayoutParams.MATCH_PARENT:
                return R.string.layout_match_parent;
            default:
                return 0;
        }
    }

    public static boolean getAdjustViewBounds(int adjustViewBoundsId){
        return adjustViewBoundsId == R.string.adjustViewBounds_true;
    }

    public static int getAdjustViewBoundsName(boolean adjustViewBounds){
        return adjustViewBounds ? R.string.adjustViewBounds_true : R.string.adjustViewBounds_false;
    }
}
