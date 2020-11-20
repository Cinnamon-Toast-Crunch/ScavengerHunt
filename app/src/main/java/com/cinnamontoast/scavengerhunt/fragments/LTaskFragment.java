package com.cinnamontoast.scavengerhunt.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinnamontoast.scavengerhunt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "totalPoints";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mTotalPoints;

    public LTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @param totalPoints Parameter 2.
     * @return A new instance of fragment LTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LTaskFragment newInstance(String name, String totalPoints) {
        LTaskFragment fragment = new LTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, totalPoints);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
            mTotalPoints = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_l_task, container, false);
    }
}