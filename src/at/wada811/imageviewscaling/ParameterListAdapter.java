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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import at.wada811.imageviewscaling.Param.ParamName;
import java.util.List;

public class ParameterListAdapter extends BaseExpandableListAdapter {

    private List<ParamName> mParamNames;
    private List<List<Integer>> mParamValues;
    private LayoutInflater mLayoutInflater;
    private ParameterAdapterListener mListener;

    public static interface ParameterAdapterListener {
        public boolean isSelected(int groupPosition, int childPosition);
    }

    public ParameterListAdapter(Context context, List<ParamName> paramNames, List<List<Integer>> paramValues, ParameterAdapterListener listener) {
        mParamNames = paramNames;
        mParamValues = paramValues;
        mListener = listener;
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount(){
        return mParamValues.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return mParamValues.get(groupPosition).size();
    }

    public ParamName getGroupName(int groupPosition){
        return mParamNames.get(groupPosition);
    }

    @Override
    public List<Integer> getGroup(int groupPosition){
        return mParamValues.get(groupPosition);
    }

    @Override
    public Integer getChild(int groupPosition, int childPosition){
        return mParamValues.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    private boolean isSelected(int groupPosition, int childPosition){
        return mListener.isSelected(groupPosition, childPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        View view;
        if(convertView == null){
            view = mLayoutInflater.inflate(R.layout.fragment_param_list_row, parent, false);
        }else{
            view = convertView;
        }
        TextView selected = (TextView)view.findViewById(R.id.selected);
        selected.setVisibility(View.INVISIBLE);
        ParamName group = getGroupName(groupPosition);
        TextView title = (TextView)view.findViewById(R.id.title);
        title.setText(group.labelId);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        View view;
        if(convertView == null){
            view = mLayoutInflater.inflate(R.layout.fragment_param_list_row, parent, false);
        }else{
            view = convertView;
        }
        TextView selected = (TextView)view.findViewById(R.id.selected);
        if(isSelected(groupPosition, childPosition)){
            selected.setVisibility(View.VISIBLE);
        }else{
            selected.setVisibility(View.INVISIBLE);
        }
        TextView title = (TextView)view.findViewById(R.id.title);
        title.setText(getChild(groupPosition, childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }
}
