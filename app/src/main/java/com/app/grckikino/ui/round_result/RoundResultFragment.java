package com.app.grckikino.ui.round_result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.grckikino.R;
import com.app.grckikino.databinding.FragmentRoundResultBinding;

public class RoundResultFragment extends Fragment {

    private FragmentRoundResultBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RoundResultViewModel ticketViewModel = new ViewModelProvider(this).get(RoundResultViewModel.class);

        binding = FragmentRoundResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        ticketViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        ticketViewModel.setText(getString(R.string.page_under_constraction));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}