package com.app.grckikino.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.grckikino.R;
import com.app.grckikino.databinding.FragmentResultsBinding;


public class ResultsFragment extends Fragment {

    private FragmentResultsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResultsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ResultsViewModel.class);

        binding = FragmentResultsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textResults;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        notificationsViewModel.setText(getString(R.string.page_under_constraction));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}