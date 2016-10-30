package com.zhao.drawerlayouttest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhao on 2016/10/30.
 */
public class PlantFragment extends Fragment {

    private static final String EXTRA_KEY= "param1";
    private static final String EXTRA_PARAM = "param2";

    private String mName;
    private String mInfo;
    private TextView mTitleView;
    private IFragmentListener mListener;

    public PlantFragment(){

    }

    public static PlantFragment newInstance(String param1,String param2){
        PlantFragment fragment = new PlantFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_KEY,param1);
        bundle.putString(EXTRA_PARAM,param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            mName = getArguments().getString(EXTRA_KEY);
            mInfo = getArguments().getString(EXTRA_PARAM);
        }else{
            throw new RuntimeException("content does not implement interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_fragmet,container,false);
        mTitleView = (TextView)view.findViewById(R.id.tv_framg);
        mTitleView.setText(mInfo+":"+mName);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        if(context instanceof  IFragmentListener){
            mListener = (IFragmentListener)context;
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface  IFragmentListener{
        void handleBus(String data);
    }
}
