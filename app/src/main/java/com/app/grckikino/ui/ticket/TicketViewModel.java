package com.app.grckikino.ui.ticket;

import static com.app.grckikino.utils.KeysAndConstants.DEFAULT_RANDOM_NUM;
import static com.app.grckikino.utils.KeysAndConstants.NUMBER_OF_BALLS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.grckikino.models.TicketNumberModel;
import com.app.grckikino.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

public class TicketViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<TicketNumberModel>> ticketNumbersList;

    private MutableLiveData<Integer> randomSpinnerNumb, selectionCount;

    public TicketViewModel() {
        mText = new MutableLiveData<>();
        ticketNumbersList = new MutableLiveData<>();
        randomSpinnerNumb = new MutableLiveData<>();
        selectionCount = new MutableLiveData<>();
        setNumbersList(NUMBER_OF_BALLS);
        setSelectionCount(0);
        randomSpinnerNumb.setValue(DEFAULT_RANDOM_NUM);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        mText.setValue(text);
    }

    public LiveData<Integer> getRandomSpinnerNumb() {
        return randomSpinnerNumb;
    }

    public void setNumbOfRandomsSpinnerNumb(int number) {
        randomSpinnerNumb.setValue(number);
    }


    public LiveData<Integer> getSelectionCount() {
        return selectionCount;
    }

    public void setSelectionCount(int number) {
        selectionCount.setValue(number);
    }

    public void countSelectedItems() {
        if (ticketNumbersList.getValue()!=null) {
            setSelectionCount(Utils.getSelectedItemCount(ticketNumbersList.getValue()));
        }
    }


    public LiveData<ArrayList<TicketNumberModel>> getTicketNumbersList() {
        return ticketNumbersList;
    }

    public void setNumbersList(int x) {
        ticketNumbersList.setValue(loadItems(x));
    }

    public void clearData() {
        ticketNumbersList.setValue(loadItems(NUMBER_OF_BALLS));
        setSelectionCount(0);
    }

    private ArrayList<TicketNumberModel> loadItems(int x) {
        ArrayList<TicketNumberModel> arrayList = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            arrayList.add(new TicketNumberModel(i, 0));
        }
        return arrayList;
    }

    public void generateAllRandomNumbers() { // ovde postoji pitanje da li ukoliko je user vec obelezio neke br, i klikne da generise random da li se ostaju ti izabrani, ili se uklone i svi se izvuku random
        if (randomSpinnerNumb.getValue() != null) {
            ArrayList<TicketNumberModel> newList = loadItems(NUMBER_OF_BALLS);
            ArrayList<TicketNumberModel> shuffleList = loadItems(NUMBER_OF_BALLS);
            int numberOfRandoms = randomSpinnerNumb.getValue();
            Collections.shuffle(shuffleList);
            for (int i = 0; i <= numberOfRandoms; i++) {
                newList.get(shuffleList.get(i).getNumber() - 1).setIsSelected(1);// -1 je zbog indexa
            }
            ticketNumbersList.setValue(newList);
            setSelectionCount(numberOfRandoms+1);
        }
    }
}