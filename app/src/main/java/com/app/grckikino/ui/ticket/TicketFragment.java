package com.app.grckikino.ui.ticket;

import static com.app.grckikino.utils.KeysAndConstants.COLUMN_NUMBERS_IN_TICKET;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grckikino.R;
import com.app.grckikino.adapters.TicketGridAdapter;
import com.app.grckikino.databinding.FragmentTicketBinding;
import com.app.grckikino.callbacks.TicketAdapterCallback;
import com.app.grckikino.ui_elements.GridSpacingDecoration;
import com.app.grckikino.utils.Utils;

import java.util.ArrayList;


public class TicketFragment extends Fragment implements TicketAdapterCallback, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private FragmentTicketBinding binding;

    private TicketViewModel viewModel;
    private TicketGridAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TicketViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTicketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setupClicks();
        setupTicketRecicler();
        setRandomNumbSpinner();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getTicketNumbersList().observe(getViewLifecycleOwner(), ticketNumberModels -> adapter.updateList(ticketNumberModels));
        viewModel.getRandomSpinnerNumb().observe(getViewLifecycleOwner(), integer -> binding.spinner.setSelection(integer));
        viewModel.getSelectionCount().observe(getViewLifecycleOwner(), integer -> {
            binding.seectedCountText.setText(String.valueOf(integer));
            binding.quotaText.setText(getString(R.string.quota, Utils.formatNumber(integer)));
            binding.paymentBtn.setEnabled(integer != 0);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupTicketRecicler() {
        RecyclerView recyclerView = binding.ticketRecyclerView;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), COLUMN_NUMBERS_IN_TICKET);// ja iskreno mislim da je previse sitno da bude 10 brojeva u jednoj koloni, msm da bi bilo ok 8 (moze se promeniti broj ovde
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new TicketGridAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingDecoration(gridLayoutManager.getSpanCount(), 0.3f, true));

    }

    private void setRandomNumbSpinner() {
        Spinner spinner = binding.spinner;
        ArrayAdapter<CharSequence> numbersAdapter = ArrayAdapter.createFromResource(getContext(), R.array.number_array, android.R.layout.simple_spinner_item);
        numbersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(numbersAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void setupClicks() {
        binding.randomBtn.setOnClickListener(this);
        binding.randomNumText.setOnClickListener(this);
        binding.clearSelectionBtn.setOnClickListener(this);
        binding.paymentBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        viewModel.setNumbOfRandomsSpinnerNumb(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.random_btn) {
            viewModel.generateAllRandomNumbers();
        } else if (view.getId() == R.id.random_num_text) {
            binding.spinner.performClick();
        } else if (view.getId() == R.id.clear_selection_btn) {
            viewModel.clearData();
        }else if (view.getId() == R.id.payment_btn) {
            Toast.makeText(getContext(), getString(R.string.page_under_constraction), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNumberClicked() {
        viewModel.countSelectedItems();
    }

    @Override
    public void onSelectedMax(int number) {
        Toast.makeText(getContext(), getString(R.string.max_number_of_selections), Toast.LENGTH_LONG).show();
    }
}