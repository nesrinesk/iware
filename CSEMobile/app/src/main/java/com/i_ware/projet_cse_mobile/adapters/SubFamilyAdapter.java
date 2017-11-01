package com.i_ware.projet_cse_mobile.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.i_ware.projet_cse_mobile.R;
import com.i_ware.projet_cse_mobile.rows.Family_row;
import com.i_ware.projet_cse_mobile.rows.SubFamily_row;

import java.util.List;

/**
 * Created by asus on 19/10/2017.
 */

public class SubFamilyAdapter extends ArrayAdapter<SubFamily_row> {

    //familyrows est la liste des models à afficher
    public SubFamilyAdapter(Context context, int activity_main, List<SubFamily_row> subFamily_rows) {
        super(context, 0, subFamily_rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_subfamily, parent, false);
        }

        SubFamilyAdapter.TweetViewHolder viewHolder = (SubFamilyAdapter.TweetViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SubFamilyAdapter.TweetViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Family_row> tweets
        SubFamily_row familyrow = getItem(position);


        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(familyrow.getRef());
        viewHolder.text.setText(familyrow.getName());
        viewHolder.avatar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.homeicon));


        return convertView;
    }

    private class TweetViewHolder {
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;
    }
}