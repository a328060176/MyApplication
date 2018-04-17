package com.example.administrator.myapplication;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Administrator on 2018/3/20.
 */

public class AllAdpter extends BaseExpandableListAdapter {
    public List<String> father;
    public List<List<String>> chilerd;
    private Context context;

    public AllAdpter(List<String> faList, List<List<String>> chList,
                     Context context) {  //初始化数据
        this.father = faList;
        this.chilerd = chList;
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chilerd.get(groupPosition).get(childPosition);   //获取父类下面的每一个子类项
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;  //子类位置
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) { //显示子类数据的iew
        View view = null;
        view = LayoutInflater.from(context).inflate(
                R.layout.all_expand_list_item, null);
        TextView textView = (TextView) view
                .findViewById(R.id.all_list_text_item_id);
        textView.setText(chilerd.get(groupPosition).get(childPosition));
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chilerd.get(groupPosition).size();  //子类item的总数
    }

    @Override
    public Object getGroup(int groupPosition) {   //父类数据
        return father.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return father.size();  ////父类item总数
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;   //父类位置
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.all_expand_list, null);
        TextView textView = (TextView) view.findViewById(R.id.all_list_text_id);
        textView.setText(father.get(groupPosition));
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {  //点击子类触发事件
        Toast.makeText(context,
                "第" + groupPosition + "大项，第" + childPosition + "小项被点击了",
                Toast.LENGTH_LONG).show();
        return true;

    }

}