package com.app.grckikino.callbacks;

import com.app.grckikino.models.RoundsModel;

public interface UpcomingRoundAdapterCallback {
    void onUpcomingDrawExpired(long id,int position);
    void onUpcomingDrawClicked(RoundsModel upcomingRound);
}
