package com.example.amitrai.internettest.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amitrai.internettest.R;

import fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = Fragment_Home.class.getName();

    private Button input_data, sort_users, usage_coasts, highest_usage, lowest_usage, search_user, exit;

    private Activity act = null;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Home.
     */
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Home() {
        // Required empty public constructor
    }

    // initializing view elements
    private void initView(View v){
        input_data = (Button) v.findViewById(R.id.inputdata);
        sort_users = (Button) v.findViewById(R.id.sort_users);
        usage_coasts = (Button) v.findViewById(R.id.usage_coast);
        highest_usage = (Button) v.findViewById(R.id.highest_usage);
        lowest_usage = (Button) v.findViewById(R.id.lowest_usage);
        search_user = (Button) v.findViewById(R.id.search_data);
        exit = (Button) v.findViewById(R.id.exit);


        input_data.setOnClickListener(this);
        sort_users.setOnClickListener(this);
        usage_coasts.setOnClickListener(this);
        highest_usage.setOnClickListener(this);
        lowest_usage.setOnClickListener(this);
        search_user.setOnClickListener(this);
        exit.setOnClickListener(this);

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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        act = getActivity();

        // initializing views
        initView(v);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        Log.e(TAG,"button clicked");

        switch (v.getId()){
            case R.id.inputdata:
                openInputFragment();
                break;

            case R.id.sort_users:
                break;

            case R.id.usage_coast:
                break;

            case R.id.highest_usage:
                break;

            case R.id.lowest_usage:
                break;

            case R.id.search_data:
                break;

            case R.id.exit:
                getActivity().onBackPressed();
                break;


        }
    }

    /**
     * opening input fragment
     */
    private void openInputFragment(){
        act.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new InputData())
                .addToBackStack(InputData.class.getSimpleName())
                .commit();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
