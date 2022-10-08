package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text_display;
    public Button  btn1, btn2, btn3, btn_plus, btn5, btn6, btn7, btn_min, btn9, btn10, btn11, btn_multi, btn_clear, btn0, btn_dot, btn_div, btn_equal;

    // This is to evaluate the math expression
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engine = new ScriptEngineManager().getEngineByName("rhino");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn_min = (Button) findViewById(R.id.btn_min);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);
        btn_multi = (Button) findViewById(R.id.btn_multi);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn0 = (Button) findViewById(R.id.btn0);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        text_display = (TextView) findViewById(R.id.textview_input_display);

        setClickListeners();
    }

    private void setClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn_min.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.btn5:
                addNumber("4");
                break;
            case R.id.btn6:
                addNumber("5");
                break;
            case R.id.btn7:
                addNumber("6");
                break;
            case R.id.btn_min:
                addNumber("-");
                break;
            case R.id.btn9:
                addNumber("7");
                break;
            case R.id.btn10:
                addNumber("8");
                break;
            case R.id.btn11:
                addNumber("9");
                break;
            case R.id.btn_multi:
                addNumber("*");
                break;
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.btn_div:
                addNumber("/");
                break;
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (ScriptException e) {
                    text_display.setText("Error");
                }
                break;
            case R.id.btn_clear:
                clear_display();
                break;
        }
    }

    private String evaluate(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}
