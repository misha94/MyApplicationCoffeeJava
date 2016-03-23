package com.example.android.myapplicationjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
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

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox toppingCheckBoxCream = (CheckBox) findViewById(R.id.toppingCheckBoxCream);
        boolean toppingCheckedCream = toppingCheckBoxCream.isChecked();

        CheckBox toppingCheckBoxChocolate = (CheckBox) findViewById(R.id.toppingCheckBoxChocolate);
        boolean toopingCheckedChocolate = toppingCheckBoxChocolate.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, toppingCheckedCream, toopingCheckedChocolate);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = quantity * 60;
        return price;
    }

    /**
     * Create summary of the order.
     *
     * @param toppingCream is whether or not the user wants whipped cream topping
     * @param toppingChocolate is whether or not the user wants whipped cream topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price, boolean toppingCream, boolean toppingChocolate) {

        String orderMassage = "Name: Popkov Mikhail" + "\nДобаваить взбитые сливки? " + toppingCream;
        orderMassage += "\nДобавить шоколад? " + toppingChocolate;
        orderMassage += "\nQuantity: " + quantity + "\nTotal: " + price + " руб." + "\nThank you!";
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
