package com.i_ware.projet_cse_mobile.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.i_ware.projet_cse_mobile.R;
import com.i_ware.projet_cse_mobile.rows.Family_row;

import java.util.List;

import static android.support.v4.content.res.TypedArrayUtils.getDrawable;

/**
 * Created by mac on 22/09/2017.
 */

public class FamilyAdapter extends ArrayAdapter<Family_row> {

    //familyrows est la liste des models à afficher
    public FamilyAdapter(Context context, int activity_main, List<Family_row> familyrows) {
        super(context, 0, familyrows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_family,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Family_row> tweets
        Family_row familyrow = getItem(position);


        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(familyrow.getRef());
        viewHolder.text.setText(familyrow.getName());
        viewHolder.avatar.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.homeicon));



        return convertView;
    }

    private class TweetViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;
    }
}
