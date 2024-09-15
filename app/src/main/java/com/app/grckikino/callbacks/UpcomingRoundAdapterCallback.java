package com.app.grckikino.callbacks;

import com.app.grckikino.models.UpcomingRoundsModel;

public interface UpcomingRoundAdapterCallback {
    void onUpcomingDrawExpired(long id,int position);
    void onUpcomingDrawClicked(UpcomingRoundsModel upcomingRound);
}
