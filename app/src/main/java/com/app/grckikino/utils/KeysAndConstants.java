package com.app.grckikino.utils;

public class KeysAndConstants {

    // intent extras keys
    public static final String UPCOMING_ROUND_EXTRAS = "upcomingRoundExtra";

    // date time formats
    public static final String TIME_FORMAT = "HH:mm";

    //number formats
    public static final String QUOTA_FORMAT = "0.##";
    public static final String TWO_DECIMAL_FORMAT = "%.2f";


    //ID igre Grcki kino
    public static final int GAME_ID = 1100; //Grcki kino ID
    public static final int LOAD_UPCOMING_ROUNDS = 20;//number of upcoming rounds to be loaded from API

    //   base Url
    public static final String API_BASE_URL = "https://api.opap.gr/draws/v3.0/";

    public static final String LIVE_DRAW_URL = "https://mozzartbet.com/sr/lotto-animation/26";

//    https://api.opap.gr/draws/v3.0/%7BgameId%7D/upcoming/20

//    https://api.opap.gr/draws/v3.0/{gameId}/{drawId}

//    https://api.opap.gr/draws/v3.0/%7BgameId%7D/draw-date/%7BfromDate%7D/%7BtoDate
    public static final int DEFAULT_RANDOM_NUM = 7; //spinner default number for random
    public static final int NUMBER_OF_BALLS = 80;
    public static final int NUMBERS_IN_COLUMN = 10;//talon numbers in 1 column
    public static final int TEXT_BECOME_RED = 20; //upcoming round timer text become red at 20

}
