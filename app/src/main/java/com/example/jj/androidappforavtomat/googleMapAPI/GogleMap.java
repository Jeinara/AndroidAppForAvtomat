package com.example.jj.androidappforavtomat.googleMapAPI;

import android.content.Context;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jj.androidappforavtomat.R;
import com.google.android.gms.maps.SupportMapFragment;



public class GogleMap extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GogleMap() {
        // Required empty public constructor
    }


    public static GogleMap newInstance() {
        GogleMap fragment = new GogleMap();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gogle_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment locationMap = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.google_map_fragment);
        if (locationMap != null) {
            LocationManager locationManager = (LocationManager) getActivity()
                    .getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null ) {
                //(new GetMap()).execute(locationMap,this);
                locationMap.getMapAsync(new MapCall());
            }
        }
    }

//    class GetMap extends AsyncTask<Object, Object, Void> {
//
//
//        @Override
//        public Void doInBackground(Object... params) {
//            SupportMapFragment imgName = (SupportMapFragment) params[0];
//            OnMapReadyCallback Par = (OnMapReadyCallback) params[1];
//
//            try {
//                imgName.getMapAsync(Par);
//            } catch (Exception e) {
//                Log.e("log_tag", "Error in http connection " + e.toString());
//            }
//            return null;
//        }
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
