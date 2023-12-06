package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float RobotPrice = 35_000;
    float amount = 1_700;
    float account = 700;
    int perecentFree = 100;
    float perecentBank = 9;
    float[] monthlyPayents = new float[24];

    private TextView countOut;
    private TextView manyMonthOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countOut = findViewById(R.id.countOut);
        manyMonthOut = findViewById(R.id.manyMonthOut);

        countOut.setText(countMonth(perecentBank, amount, perecentFree, RobotPrice, account, monthlyPayents) + "месяцев");
        String monthlyPaymentsList = "";
        for(float list : monthlyPayents) {
            if (list > 0) {
                monthlyPaymentsList += Float.toString(list) + ", ";
            } else {
                break;
            }
        }
        manyMonthOut.setText("Первоначальный взнос" + account + "монет, ежемесячные выплаты (монет):" + monthlyPaymentsList);
    }

    public int countMonth(float perecentBankYear, float amount, int percent, float Robot, float accumulation, float[] arrayPayments) {

        float percentBankMonth = perecentBankYear / 12;
        float mortgageCosts = (amount*percent)/100;
        float total = Robot - accumulation;
        int count = 0;

        while (total>0) {
            count++;
            total = (total + (total * percentBankMonth) / 100) - mortgageCosts;
            if (total > mortgageCosts) {
                arrayPayments[count - 1] = mortgageCosts;
            } else {
                arrayPayments[count - 1] = total;
            }
        }

        return count;
    }
}