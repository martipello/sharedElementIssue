package com.sealstudios.sharedelement;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sealstudios.sharedelement.BlankFragmentDirections.ActionBlankFragmentToBlankFragment2;
import java.util.ArrayList;


public class BlankFragment extends Fragment implements ItemTouchListener {

    private BlankFragmentAdapter adapter;
    private RecyclerView recyclerView;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Card> cardArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.ic_launcher_background);
            cardArrayList.add(new Card("card text " + i, d));
        }
        recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4,RecyclerView.VERTICAL, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        BlankFragmentAdapter adapter = new BlankFragmentAdapter(cardArrayList, this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view, int position) {

        NavController navController = Navigation.findNavController(recyclerView);

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(view, view.getTransitionName()).build();

        BlankFragmentDirections.ActionBlankFragmentToBlankFragment2 directions = BlankFragmentDirections.actionBlankFragmentToBlankFragment2(view.getTransitionName());

        navController.navigate(directions,extras);

    }

    @Override
    public boolean onLongClick(View view, int position) {
        return false;
    }
}
