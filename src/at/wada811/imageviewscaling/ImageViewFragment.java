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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import at.wada811.imageviewscaling.ParameterListFragment.ParameterDelegate;

public class ImageViewFragment extends Fragment {

    private ImageView mImageView;

    public static ImageViewFragment newInstance(){
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ImageViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mImageView = (ImageView)rootView.findViewById(R.id.imageView);
        return rootView;
    }

    public ParameterDelegate getParameterDelegate(){
        return new ParameterDelegate(){
            @Override
            public ScaleType getScaleType(){
                return mImageView.getScaleType();
            }

            @Override
            public void setScaleType(ScaleType scaleType){
                mImageView.setScaleType(scaleType);
            }

            @Override
            public int getLayoutParamsWidth(){
                return mImageView.getLayoutParams().width;
            }

            @Override
            public void setLayoutParamsWidth(int width){
                LayoutParams params = mImageView.getLayoutParams();
                params.width = width;
                mImageView.setLayoutParams(params);
            }

            @Override
            public int getLayoutParamsHeight(){
                return mImageView.getLayoutParams().height;
            }

            @Override
            public void setLayoutParamsHeight(int height){
                LayoutParams params = mImageView.getLayoutParams();
                params.height = height;
                mImageView.setLayoutParams(params);

            }

            @Override
            public boolean getAdjustViewBounds(){
                return mImageView.getAdjustViewBounds();
            }

            @Override
            public void setAdjustViewBounds(boolean adjustViewBounds){
                mImageView.setAdjustViewBounds(adjustViewBounds);
            }

        };
    }

}
