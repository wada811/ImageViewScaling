/*
 * Copyright 2013 wada811<at.wada811@gmail.com>
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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView.ScaleType;
import at.wada811.app.fragment.ExpandableListFragment;
import at.wada811.imageviewscaling.Param.ParamName;
import at.wada811.imageviewscaling.ParameterListAdapter.ParameterAdapterListener;
import java.util.ArrayList;
import java.util.List;

public class ParameterListFragment extends ExpandableListFragment implements ParameterAdapterListener {

    private ParameterDelegate mDelegate;
    private ParameterListAdapter mAdapter;

    public interface ParameterDelegateProvider {

        public ParameterDelegate getParameterDelegate();

    }

    public interface ParameterDelegate {

        public ScaleType getScaleType();

        public void setScaleType(ScaleType scaleType);

        public int getLayoutParamsWidth();

        public void setLayoutParamsWidth(int width);

        public int getLayoutParamsHeight();

        public void setLayoutParamsHeight(int height);

        public boolean getAdjustViewBounds();

        public void setAdjustViewBounds(boolean adjustViewBounds);

        public void setFitDisplayInside();

    }

    public static ParameterListFragment newInstance(){
        ParameterListFragment fragment = new ParameterListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        if(activity instanceof ParameterDelegateProvider == false){
            throw new ClassCastException(activity.getLocalClassName() + " must implements " + ParameterDelegateProvider.class.getSimpleName());
        }
        ParameterDelegateProvider provider = (ParameterDelegateProvider)activity;
        mDelegate = (ParameterDelegate)provider.getParameterDelegate();
    }

    @Override
    public void onResume(){
        super.onResume();
        List<ParamName> paramNames = new ArrayList<ParamName>();
        List<List<Integer>> paramValues = new ArrayList<List<Integer>>();
        for(ParamName paramName : ParamName.values()){
            paramNames.add(paramName);
            paramValues.add(Param.getValues(paramName));
        }
        // setListAdapter 
        mAdapter = new ParameterListAdapter(getActivity(), paramNames, paramValues, this);
        setListAdapter(mAdapter);
        // ExpandableListView options
        getExpandableListView().setScrollingCacheEnabled(false);
    }

    @Override
    public void onGroupExpand(int groupPosition){
        super.onGroupExpand(groupPosition);
    }

    @Override
    public void onGroupCollapse(int groupPosition){
        super.onGroupCollapse(groupPosition);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id){
        switch(mAdapter.getGroupName(groupPosition)){
            case fitDisplayInside:
                mDelegate.setFitDisplayInside();
                return true;
            default:
                return super.onGroupClick(parent, v, groupPosition, id);
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
        parent.collapseGroup(groupPosition);
        switch(mAdapter.getGroupName(groupPosition)){
            case scaleType:{
                Integer scaleTypeId = mAdapter.getChild(groupPosition, childPosition);
                mDelegate.setScaleType(Param.getScaleType(scaleTypeId));
            }
                break;
            case layout_width:{
                Integer layoutId = mAdapter.getChild(groupPosition, childPosition);
                mDelegate.setLayoutParamsWidth(Param.getLayoutParams(layoutId));
            }
                break;
            case layout_height:{
                Integer layoutId = mAdapter.getChild(groupPosition, childPosition);
                mDelegate.setLayoutParamsHeight(Param.getLayoutParams(layoutId));
            }
                break;
            case adjustViewBounds:{
                Integer adjustViewBoundsId = mAdapter.getChild(groupPosition, childPosition);
                mDelegate.setAdjustViewBounds(Param.getAdjustViewBounds(adjustViewBoundsId));
            }
                break;
            default:
                break;
        }
        parent.expandGroup(groupPosition);
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    @Override
    public boolean isSelected(int groupPosition, int childPosition){
        switch(mAdapter.getGroupName(groupPosition)){
            case scaleType:
                ScaleType scaleType = mDelegate.getScaleType();
                return Param.getScaleTypeName(scaleType) == mAdapter.getChild(groupPosition, childPosition);
            case layout_width:
                int layout_width = mDelegate.getLayoutParamsWidth();
                return Param.getLayoutParamsName(layout_width) == mAdapter.getChild(groupPosition, childPosition);
            case layout_height:
                int layout_height = mDelegate.getLayoutParamsHeight();
                return Param.getLayoutParamsName(layout_height) == mAdapter.getChild(groupPosition, childPosition);
            case adjustViewBounds:
                boolean adjustViewBounds = mDelegate.getAdjustViewBounds();
                return Param.getAdjustViewBoundsName(adjustViewBounds) == mAdapter.getChild(groupPosition, childPosition);
            default:
                break;
        }
        return false;
    }

}
