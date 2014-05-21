package com.tiz.android.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiz.android.R;

/**
 * Created by david.hodge on 12/23/13.
 */
public class Aboutfragment extends Fragment {

    View view;
    Context mContext;
    TextView buildVersion;
    Typeface robotoThin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.about_fragment, container, false);
        buildVersion = (TextView) view.findViewById(R.id.about_build);
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Thin.ttf");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity().getBaseContext();

        buildVersion.setText("v1.0.0 b1");
        buildVersion.setTypeface(robotoThin);
    }
}
