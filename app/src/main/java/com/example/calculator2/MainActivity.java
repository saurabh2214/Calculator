package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result,solution;
    MaterialButton buttonac,buttonc,buttonbro,buttonbrc,button7,button8,button9,buttondiv,button4,button5,button6,buttonmul,button1,button2,button3,buttonmin,buttondot,button0,buttoneqaul,buttonadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result_tv);
        solution=findViewById(R.id.solution_tv);

        assignId(buttonac,R.id.button_ac);
        assignId(buttonc,R.id.button_c);
        assignId(buttonbro,R.id.button_open_bracket);
        assignId(buttonbrc,R.id.button_close_bracket);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(buttonadd,R.id.button_add);
        assignId(buttonmin,R.id.button_minus);
        assignId(buttonmul,R.id.button_multiply);
        assignId(buttondiv,R.id.button_divide);
        assignId(buttondot,R.id.button_decimal);
        assignId(buttoneqaul,R.id.button_equal);

    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonV = button.getText().toString();
        String data = solution.getText().toString();

        if(buttonV.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }
        if(buttonV.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonV.equals("C")){
            if(data.length()>1)
            data=data.substring(0,data.length()-1);
            else {
                solution.setText("");
                result.setText("0");
                return;
            }

        }
        else
        {
            data=data+buttonV;
        }
        solution.setText(data);

        String finalResult =getResult(data);

        if(!finalResult.equals("Error"))
            result.setText(finalResult);
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable =context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith((".0"))){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e)
        {
            return "Error";
        }
    }
}