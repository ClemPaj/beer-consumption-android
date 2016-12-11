package com.clempaj.beerconsumption.db;

import android.provider.BaseColumns;

public final class BeerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BeerContract() {
    }

    /* Inner class that defines the table contents */
    public static class BeerEntry implements BaseColumns {
        public static final String TABLE_NAME = "beer";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ALCOHOL = "alcohol";
    }

    public static class ConsumptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "consumption";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_BEER_ID = "beerId";
        public static final String COLUMN_NAME_VOLUME = "volume";
    }
}