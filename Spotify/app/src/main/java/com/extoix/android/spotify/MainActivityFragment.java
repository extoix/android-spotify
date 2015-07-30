package com.extoix.android.spotify;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

public class MainActivityFragment extends Fragment {

    ArtistAdapter mArtistAdapter;
    List<Artist> mArtistList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SearchTask searchTask = new SearchTask();
        searchTask.execute();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView artistListView = (ListView) rootView.findViewById(R.id.artist_list);

        mArtistAdapter = new ArtistAdapter(getActivity(), new ArrayList<Artist>());
        artistListView.setAdapter(mArtistAdapter);

        return rootView;
    }

    public class SearchTask extends AsyncTask<Void, Void, List<Artist>> {

        @Override
        protected List<Artist> doInBackground(Void... strings) {

            SpotifyApi spotifyApi = new SpotifyApi();
            SpotifyService spotifyService = spotifyApi.getService();

            ArtistsPager artistResults = spotifyService.searchArtists("Cake");

            List<Artist> artistList = artistResults.artists.items;
            return artistList;
        }

        @Override
        protected void onPostExecute(List<Artist> artists) {
            if (artists != null) {
                mArtistAdapter.clear();
                mArtistAdapter.addAll(artists);
            }
        }
    }

}
