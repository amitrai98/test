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
import android.widget.EditText;

import com.example.amitrai.internettest.App.Constant;
import com.example.amitrai.internettest.R;
import com.example.amitrai.internettest.modal.UsageModal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletionService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InputData.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InputData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputData extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button save, cancel;
    private EditText edt_acc_no, edt_usage;
    private final String TAG = InputData.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputData.
     */
    // TODO: Rename and change types and number of parameters
    public static InputData newInstance(String param1, String param2) {
        InputData fragment = new InputData();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public InputData() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(Constant.list.size()>0){
            showAllEntriesInShortedOrder();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_data, container, false);
        initView(v);
        return v;
    }

    /**
     * initializing view elements.
     */
    private void initView(View v){
        save = (Button) v.findViewById(R.id.save);
        cancel = (Button) v.findViewById(R.id.cancel);
        edt_acc_no = (EditText) v.findViewById(R.id.edt_acc_no);
        edt_usage = (EditText) v.findViewById(R.id.edt_usage);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                getActivity().onBackPressed();
                break;

            case R.id.save:
                saveData();
                break;
        }

    }

    /**
     * saving data
     */
    private void saveData(){

        // validating input
        boolean cancel = false;

        View focurs_view = null;

        String acc_no = edt_acc_no.getText().toString().trim();
        String  temp_usage = edt_usage.getText().toString().trim();
        int usage = 0;
        try{
            usage = Integer.parseInt(temp_usage);
        }catch (Exception e){
            e.printStackTrace();
        }

        edt_acc_no.setError(null);
        edt_usage.setError(null);

        if(acc_no.length() <1){
            cancel = true;
            focurs_view = edt_acc_no;
            edt_acc_no.setError("Acc No can not be left blank");
        }else if(temp_usage.length()<1){
            cancel = true;
            focurs_view = edt_usage;
            edt_usage.setError("Usage can not be left blank");
        }

        if(cancel){
            focurs_view.requestFocus();
        }else {
            // todo code for saving the data
            Constant.list.add(new UsageModal(acc_no, usage, 0));
            edt_acc_no.setText("");
            edt_usage.setText("");
            UsageModal modal = null;

            if(Constant.list.size()>0)
                modal = Constant.list.get(Constant.list.size() - 1);

            if(modal != null)
                Log.e(TAG, " acc "+modal.getAccount_no() + " usage "+modal.getUsage()+" coast "+modal.getCoast());
        }
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
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    /**
     * sorting list objects
     */
    private void showAllEntriesInShortedOrder(){
        if (Constant.list.size() > 0) {
            Collections.sort(Constant.list, new Comparator<UsageModal>() {
                @Override
                public int compare(final UsageModal object1, final UsageModal object2) {
                    return object1.getAccount_no().compareTo(object2.getAccount_no());
                }
            });
        }

        List<UsageModal> list = new ArrayList<>();
            list.addAll(Constant.list);
        UsageModal modal = null;
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                modal = list.get(i);
                if(modal != null)
                    Log.e(TAG, " acc "+modal.getAccount_no() + " usage "+modal.getUsage()+" coast "+modal.getCoast());
            }
        }

    }

}
