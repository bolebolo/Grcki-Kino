package com.app.grckikino.ui.upcoming_rounds;

import static com.app.grckikino.utils.KeysAndConstants.UPCOMING_ROUND_EXTRAS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grckikino.activities.RoundActivity;
import com.app.grckikino.adapters.UpcomingRoundsListAdapter;
import com.app.grckikino.databinding.FragmentUpcomingDrawsBinding;
import com.app.grckikino.callbacks.UpcomingRoundAdapterCallback;
import com.app.grckikino.models.UpcomingRoundsModel;

import java.util.ArrayList;

public class UpcomingRoundsFragment extends Fragment implements UpcomingRoundAdapterCallback {

    private FragmentUpcomingDrawsBinding binding;
    private UpcomingRoundsViewModel viewModel;
    private RecyclerView recyclerView;
    private UpcomingRoundsListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UpcomingRoundsViewModel.class);
        viewModel.initList();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpcomingDrawsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.upcomingDrawsRecyclerView;
        setUpcomingDrawsAndAdapter();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getUpcomingDrawsList().observe(getViewLifecycleOwner(), upcomingDrawsModels -> adapter.updateList(upcomingDrawsModels));
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "onResume ");
        viewModel.removeAllExpired();
    }

    @Override
    public void onDestroy() {
        Log.e("onDestroy", "onDestroy ");
        adapter.stopHandler();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setUpcomingDrawsAndAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UpcomingRoundsListAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onUpcomingDrawExpired(long id, int position) {
        viewModel.removeItem(id, position);
    }

    @Override
    public void onUpcomingDrawClicked(UpcomingRoundsModel upcomingRound) {
        if (upcomingRound != null) {
            Intent intent = new Intent(getContext(), RoundActivity.class);
            Bundle patrolBundle = new Bundle();
            patrolBundle.putParcelable(UPCOMING_ROUND_EXTRAS, upcomingRound);
            intent.putExtra(UPCOMING_ROUND_EXTRAS, patrolBundle);
            startActivity(intent);
        }
    }
}