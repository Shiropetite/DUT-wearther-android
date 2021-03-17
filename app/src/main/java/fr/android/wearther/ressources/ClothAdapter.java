package fr.android.wearther.ressources;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.android.wearther.R;
import fr.android.wearther.data.Cloth;

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
        convertView = inflater.inflate(R.layout.adapter_cloth, null);
        Cloth currentItem = getItem(position);

        String itemName = currentItem.getName();
        int reaId = context.getResources().getIdentifier("v_" + itemName, "drawable", context.getPackageName());

        TextView itemNameView = convertView.findViewById(R.id.item_name);
        String maj = itemName.charAt(0) + "";
        itemNameView.setText(maj.toUpperCase() + itemName.substring(1));

        ImageView itemIconView = convertView.findViewById(R.id.item_icon);
        itemIconView.setImageResource(reaId);

        return convertView;
    }
}
