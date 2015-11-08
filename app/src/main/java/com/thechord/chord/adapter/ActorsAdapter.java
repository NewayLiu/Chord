package com.thechord.chord.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.R;
import com.thechord.chord.entity.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neway on 2015/11/8.
 */
public class ActorsAdapter extends BaseAdapter {

    private List<Actor> actorList = new ArrayList<>();
    private Context context;

    public ActorsAdapter(Context context) {
        this.context = context;
    }

    public void updateData(List<Actor> actorList){
        this.actorList = actorList;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return actorList.size();
    }

    @Override
    public Actor getItem(int position) {
        return actorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(actorList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_actors,parent,false);
        }
        ImageView imgViewActorAvatar = (ImageView) convertView.findViewById(R.id.imgView_actor_avatar);
        TextView txtViewName = (TextView) convertView.findViewById(R.id.txtView_actor_name);
        ImageLoader.getInstance().displayImage(actorList.get(position).getAvatars().getLarge(),imgViewActorAvatar);
        txtViewName.setText(actorList.get(position).getName());
        return convertView;
    }
}
