package com.example.android.myapplicationjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;
    int toppingCreamPrice = 20;
    int toppingChocolatePrice = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity >50) {

            Context context = getApplicationContext();
            CharSequence text = "Вы не можете заказать больше 50 чашек кофе";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {

            Context context = getApplicationContext();
            CharSequence text = "Вы не можете заказать меньше 1 чашки кофе";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
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

        EditText textEditable = (EditText) findViewById(R.id.textEdit);
        String textEdit = textEditable.getText().toString();

        int price = calculatePrice();

        String priceMessage = createOrderSummary(price, toppingCheckedCream, toopingCheckedChocolate, textEdit);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, textEdit));
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
    private String createOrderSummary(int price, boolean toppingCream, boolean toppingChocolate, String editText) {
          if (toppingCream) {
              price += (toppingCreamPrice * quantity);
          }
        if (toppingChocolate) {
            price += (toppingChocolatePrice * quantity);
        }

        String orderMassage = getString(R.string.order_summary_name, editText);
        orderMassage += "\n" + getString(R.string.order_summary_whipped_cream, toppingCream);
        orderMassage += "\n" + getString(R.string.order_summary_chocolate,toppingChocolate);
        orderMassage += "\n" + getString(R.string.order_summary_quantity, quantity);
        orderMassage += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        orderMassage += "\n" + getString(R.string.thank_you);
        return orderMassage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
