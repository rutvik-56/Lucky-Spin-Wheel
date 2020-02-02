package com.example.luckyspinwheel;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import github.hellocsl.cursorwheel.CursorWheelLayout;

public class wheeltextadapter extends CursorWheelLayout.CycleWheelAdapter {

    private Context mContext;
    private List<MenuItemData> menuitem;
    private LayoutInflater inflater;
    private int gravity;

    public wheeltextadapter(Context mContext, List<MenuItemData> menuitem) {
        this.mContext = mContext;
        this.menuitem = menuitem;
        gravity= Gravity.CENTER;
        inflater=LayoutInflater.from(mContext);
    }

    public wheeltextadapter(Context mContext, List<MenuItemData> menuitem, int gravity) {
        this.mContext = mContext;
        this.menuitem = menuitem;
        this.gravity = gravity;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return menuitem.size();
    }

    @Override
    public View getView(View parent, int position) {

        MenuItemData itemData=(MenuItemData)getItem(position);
        View root=inflater.inflate(R.layout.wheel_text_layout,null,false);
        TextView textView=(TextView)root.findViewById(R.id.wheel_menu_item_tv);
        textView.setVisibility(View.VISIBLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        textView.setText(itemData.mTitle);

        if(textView.getLayoutParams() instanceof LinearLayout.LayoutParams)
            ((LinearLayout.LayoutParams)textView.getLayoutParams()).gravity=gravity;


        return root;
    }

    @Override
    public Object getItem(int position) {
        return menuitem.get(position);
    }
}
