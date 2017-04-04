package net.yumig.mkmj.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.yumig.mkmj.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : Gaoxichao
 * Date       : 2017/3/24 15:26
 */

public class ShopCarFragment extends Fragment {

    private View   view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(this.getActivity(), R.layout.fragment_shopcar, null);
        return view;
    }

}
