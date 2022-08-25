package com.example.ordercoffe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewToppings;
    private CheckBox checkMilk;
    private CheckBox checkSugar;
    private CheckBox checkLemon;
    private Spinner spinTea;
    private Spinner spinCoffee;
    private StringBuilder stringBuilder;
    private String name;
    private String password;
    private String drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        textViewHello = findViewById(R.id.textViewHello);
        textViewHello.setText(String.format(getString(R.string.textViewHelloName), name));
        textViewToppings = findViewById(R.id.textViewAddToppings);
        drink = getString(R.string.tea);
        textViewToppings.setText(String.format(getString(R.string.textViewAddToppings), drink));
        spinTea = findViewById(R.id.spin_tea);
        spinCoffee = findViewById(R.id.spin_coffee);
        checkMilk = findViewById(R.id.milk);
        checkSugar = findViewById(R.id.sugar);
        checkLemon = findViewById(R.id.lemon);
        stringBuilder = new StringBuilder();
    }

    public void onClickRadio(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if(id == R.id.radioTea){
            drink = getString(R.string.tea);
            spinTea.setVisibility(View.VISIBLE);
            spinCoffee.setVisibility(View.INVISIBLE);
            checkLemon.setVisibility(View.VISIBLE);
        }else{
            drink = getString(R.string.coffee);
            spinTea.setVisibility(View.INVISIBLE);
            spinCoffee.setVisibility(View.VISIBLE);
            checkLemon.setVisibility(View.INVISIBLE);
         }
        textViewToppings.setText(String.format(getString(R.string.textViewAddToppings), drink));

    }

    public void onClickSendOrder(View view) {

        String toppings ="";
        stringBuilder.setLength(0);

        if(checkMilk.isChecked()){
            stringBuilder.append("Молоко").append("\n");
        }if(checkSugar.isChecked()){
            stringBuilder.append("Сахар").append("\n");
        }if(checkLemon.isChecked() && drink.equals("Чай")){
            stringBuilder.append("Лемон").append("\n");
        }
        toppings = stringBuilder.toString();
        String typeDrinks = "";
        if(drink.equals("Чай")){
            typeDrinks = spinTea.getSelectedItem().toString();
        }else{
            typeDrinks = spinCoffee.getSelectedItem().toString();
        }

        String summaryOrders = String.format("Name: %s\n Pass: %s\n What drinks? %s\n What type Drinks? %s", name, password, drink, typeDrinks);

        if(stringBuilder.length() > 0){
            summaryOrders += "\n" + toppings;
        }

        Intent intent = new Intent(this, SummaryOrderActivity.class);
        intent.putExtra("order", summaryOrders);
        startActivity(intent);

    }
}