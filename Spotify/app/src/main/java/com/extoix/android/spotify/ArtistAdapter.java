package com.extoix.android.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    public ArtistAdapter(Context context, List<Artist> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_artist, parent, false);
        }

        Artist artist = getItem(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.list_item_icon);
        List<Image> imagesList = artist.images;

        if(imagesList.size() != 0) {
            int lastImageIndex = imagesList.size() - 1;
            String imageURL = imagesList.get(lastImageIndex).url;
            Picasso.with(getContext()).load(imageURL).into(imageView);
        }

        return convertView;
    }
}