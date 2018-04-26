package com.ramirez.peliculas;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "LIST";
    private static final String ARG_FAV = "FAV";

    // TODO: Rename and change types of parameters
    private ArrayList<Pelicula> lista;
    private boolean fav;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OnFragmentInteractionListener mListener;
    Bundle bundle;
    public TabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment newInstance(ArrayList<Pelicula> param1, Boolean param2) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, param1);
        args.putBoolean(ARG_FAV, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lista = (ArrayList<Pelicula>) getArguments().getSerializable(ARG_LIST);
            fav = getArguments().getBoolean(ARG_FAV);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        swipeRefreshLayout = v.findViewById(R.id.swiper);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view_tab2);
        recyclerView.setAdapter(new RecyclerViewAdapter_cardview(lista) {
            public void onFavClick(CompoundButton buttonView, boolean isChecked) {
                //mListener.onFragmentInteraction(buttonView,isChecked);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(CompoundButton buttonView, boolean isChecked) {
        if (mListener != null) {
            mListener.onFragmentInteraction(CompoundButton buttonView, boolean isChecked);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(CompoundButton buttonView, boolean isChecked);
    }

    private void initiateRefresh() {
        newInstance(lista,true);
        lista.remove(0);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);
    }
}
