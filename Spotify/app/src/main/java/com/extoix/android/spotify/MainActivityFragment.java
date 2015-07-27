package com.extoix.android.spotify;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SearchTask searchTask = new SearchTask();
        searchTask.execute();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    public class SearchTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... strings) {

            SpotifyApi spotifyApi = new SpotifyApi();
            SpotifyService spotifyService = spotifyApi.getService();

            ArtistsPager artistResults = spotifyService.searchArtists("Cake");

            List<Artist> artistList = artistResults.artists.items;
            for(Artist artist: artistList) {
                String artistName = artist.name;

                List<Image> imagesList = artist.images;
                int lastImageIndex = imagesList.size() - 1;
                String imageURL = imagesList.get(lastImageIndex).url;

                String something = "";
            }

            return null;
        }
    }

}
