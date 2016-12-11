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
        public static final String TABLE_COLUMN_NAME = TABLE_NAME + "." + COLUMN_NAME_NAME;
        public static final String TABLE_COLUMN_ALCOHOL = TABLE_NAME + "." + COLUMN_NAME_ALCOHOL;
        public static final String TABLE_COLUMN_ID = TABLE_NAME + "." + _ID;
    }

    public static class ConsumptionEntry implements BaseColumns {
        public static final String TABLE_NAME = "consumption";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_BEER_ID = "beerId";
        public static final String COLUMN_NAME_VOLUME = "volume";
        public static final String TABLE_COLUMN_DATE = TABLE_NAME + "." + COLUMN_NAME_DATE;
        public static final String TABLE_COLUMN_BEER_ID = TABLE_NAME + "." + COLUMN_NAME_BEER_ID;
        public static final String TABLE_COLUMN_VOLUME = TABLE_NAME + "." + COLUMN_NAME_VOLUME;
    }
}