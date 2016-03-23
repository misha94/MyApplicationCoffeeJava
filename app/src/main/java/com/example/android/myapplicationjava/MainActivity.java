package com.example.android.myapplicationjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity = quantity+1;
        displayQuantity(quantity);
    }
    public void decrement(View view) {
        quantity = quantity-1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
            int price = calculatePrice();
            String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = quantity * 60;
        return price;
    }

    private String createOrderSummary(int price) {
        String orderMassage = "Name: Popkov Mikhail"+ "" + "\nQuantity: " + quantity + "\nTotal: " + price + " руб." + "\nThank you!";
        return orderMassage;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
