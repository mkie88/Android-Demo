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

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MyCursorAdapter extends CursorAdapter {
    private int LoanAmountColIndex, DownPaymentColIndex, AnnualInterestColIndex, TermColIndex, CompoundColIndex,
    MonthlyRepaymentColIndex, TotalRepaymentColIndex, TotalInterestColIndex, AverageMonthlyInterestColIndex;

    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LoanAmountColIndex = cursor.getColumnIndexOrThrow(Database.COL_LOAN_AMOUNT);
        DownPaymentColIndex = cursor.getColumnIndexOrThrow(Database.COL_DOWN_PAYMENT);
        AnnualInterestColIndex = cursor.getColumnIndexOrThrow(Database.COL_ANNUAL_RATE);
        TermColIndex = cursor.getColumnIndexOrThrow(Database.COL_TERM);
        CompoundColIndex = cursor.getColumnIndexOrThrow(Database.COL_COMPOUND);
        MonthlyRepaymentColIndex = cursor.getColumnIndexOrThrow(Database.COL_MONTHLY_REPAYMENT);
        TotalRepaymentColIndex = cursor.getColumnIndexOrThrow(Database.COL_TOTAL_REPAYMENT);
        TotalInterestColIndex = cursor.getColumnIndexOrThrow(Database.COL_TOTAL_INTEREST);
        AverageMonthlyInterestColIndex = cursor.getColumnIndexOrThrow(Database.COL_AVERAGE_MONTHLY_INTEREST);

        View view = LayoutInflater.from(context).inflate(R.layout.item_history_card, viewGroup, false);

        HistoryViewHolder viewHolder = new HistoryViewHolder();
        viewHolder.tvLoanAmount = (TextView) view.findViewById(R.id.tvLoanAmount);
        viewHolder.tvDownPayment = (TextView) view.findViewById(R.id.tvDownPayment);
        viewHolder.tvTerm = (TextView) view.findViewById(R.id.tvTerm);
        viewHolder.tvAnnualInterestRate = (TextView) view.findViewById(R.id.tvAnnualInterestRate);
        viewHolder.tvCompound = (TextView) view.findViewById(R.id.compound);
        viewHolder.tvMonthlyRepayment = (TextView) view.findViewById(R.id.monthly_repayment);
        viewHolder.tvTotalRepayment = (TextView) view.findViewById(R.id.total_repayment);
        viewHolder.tvTotalInterest = (TextView) view.findViewById(R.id.total_interest);
        viewHolder.tvAverageMonthlyInterest = (TextView) view.findViewById(R.id.average_month_interest);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        HistoryViewHolder viewHolder = (HistoryViewHolder) view.getTag();

        viewHolder.tvLoanAmount.setText(String.format("%.2f", cursor.getDouble(LoanAmountColIndex)));
        viewHolder.tvDownPayment.setText(String.format("%.2f", cursor.getDouble(DownPaymentColIndex)));
        viewHolder.tvTerm.setText(String.format("%.2f", cursor.getDouble(TermColIndex)));
        viewHolder.tvAnnualInterestRate.setText(String.format("%.2f", cursor.getDouble(AnnualInterestColIndex)));
        viewHolder.tvCompound.setText(cursor.getString(CompoundColIndex));
        viewHolder.tvMonthlyRepayment.setText(String.format("%.2f", cursor.getDouble(MonthlyRepaymentColIndex)));
        viewHolder.tvTotalRepayment.setText(String.format("%.2f", cursor.getDouble(TotalRepaymentColIndex)));
        viewHolder.tvTotalInterest.setText(String.format("%.2f", cursor.getDouble(TotalInterestColIndex)));
        viewHolder.tvAverageMonthlyInterest.setText(String.format("%.2f", cursor.getDouble(AverageMonthlyInterestColIndex)));
    }
}
