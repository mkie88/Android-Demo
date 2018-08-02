/*
 * Copyright 2017 (C) CodePlay Studio. All rights reserved.
 *
 * All source code within this app is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.mike.loancalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Database {
    public static final String COL_LOAN_AMOUNT = "LoanAmount";
    public static final String COL_DOWN_PAYMENT = "DownPayment";
    public static final String COL_TERM = "Term";
    public static final String COL_ANNUAL_RATE = "AnnualInterestRate";
    public static final String COL_COMPOUND = "IsCompound";

    public static final String COL_MONTHLY_REPAYMENT = "MonthlyRepayment";
    public static final String COL_TOTAL_REPAYMENT = "TotalRepayment";
    public static final String COL_TOTAL_INTEREST = "TotalInterest";
    public static final String COL_AVERAGE_MONTHLY_INTEREST = "AverageMonthlyInterest";
    private static final String TABLENAME = "CalculationResult";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLENAME + " (" +
            BaseColumns._ID + " integer primary key autoincrement, " +
            COL_LOAN_AMOUNT + " real not null, " +
            COL_DOWN_PAYMENT + " real not null, " +
            COL_TERM + " real not null, " +
            COL_ANNUAL_RATE + " real not null, " +
            COL_COMPOUND + " text not null, " +
            COL_MONTHLY_REPAYMENT + " real not null, " +
            COL_TOTAL_REPAYMENT + " real not null, " +
            COL_TOTAL_INTEREST + " real not null, " +
            COL_AVERAGE_MONTHLY_INTEREST + " real not null);";


    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public Database(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

    public Cursor query() {
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        return db.query(
                TABLENAME,  // The table to query
                null,       // The columns to return (null to return all columns)
                null,       // The columns for the WHERE clause
                null,       // The values for the WHERE clause
                null,       // the row groups
                null,       // filter by row groups
                null        // The sort order
        );
    }

    public long InsertResult(ContentValues cv){
        long result = 0;
        SQLiteDatabase sqlLite = mySQLiteOpenHelper.getReadableDatabase();
        try {
            sqlLite.beginTransaction();
            result = sqlLite.insert(TABLENAME, null, cv);
            sqlLite.setTransactionSuccessful();
        }
        catch (SQLException ex) {

        }
        finally {
            sqlLite.endTransaction();
        }
        return result;
    }

    public void close() {
        if (mySQLiteOpenHelper!=null)
            mySQLiteOpenHelper.close();
    }

    private class MySQLiteOpenHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "database.db";
        private static final int DATABASE_VERSION = 2;

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
            }catch (SQLException ex){

            } finally {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);

            onCreate(sqLiteDatabase);
        }
    }
}
