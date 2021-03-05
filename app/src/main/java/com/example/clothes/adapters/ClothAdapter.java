package com.example.clothes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clothes.ressources.Cloth;
import com.example.clothes.R;

import java.util.List;

public class ClothAdapter extends BaseAdapter {
    private Context context;
    private List<Cloth> clothes;
    private LayoutInflater inflater;

    public ClothAdapter(Context context, List<Cloth> clothes) {
        this.context = context;
        this.clothes = clothes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return clothes.size();
    }

    @Override
    public Cloth getItem(int position) {
        return clothes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_item, null);
        Cloth currentItem = getItem(position);

        String itemName = currentItem.getName();
        int reaId = context.getResources().getIdentifier(itemName,"drawable", context.getPackageName());

        TextView itemNameView = convertView.findViewById(R.id.item_name);
        itemNameView.setText(itemName);

        ImageView itemIconView = convertView.findViewById(R.id.item_icon);
        itemIconView.setImageResource(reaId);

        return convertView;
    }
}
