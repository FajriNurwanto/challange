package com.example.fajrinurwanto.challange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        // 1. Change car engine
        //
        Car car = new Car();
        Engine v6Engine = new V6Engine();

        // PROBLEM
        double v6EngineSpeed = car.getMaxSpeed();
        System.out.println("1.1 : " + (v6EngineSpeed == v6Engine.getMaxSpeed()));

        Engine turbopropEngine = new TurbopropEngine();
        car.changeEngine(turbopropEngine);
        double turbopropEngineSpeed = car.getMaxSpeed();

        // PROBLEM
        System.out.println("1.2 : " + (turbopropEngineSpeed == v6EngineSpeed));

        //
        // 2. Custom HTML-like markup language
        //
        MarkupElement root = new RootElement("[customml]", "[/customml]");
        MarkupElement body = new BodyElement("[body]", "[/body]");
        MarkupElement italic = new ItalicElement("[i]", "[/i]");

        italic.setContent("I am italic.");

        // PROBLEM
        System.out.println("2.1 : " + italic.produceOutput()
                .equals("[i]I am italic.[/i]"));

        root.addChildren(body);
        body.addChildren(italic);

        System.out.println("2.2 : " + root.produceOutput()
                .equals("[customml][body][i]I am italic.[/i][/body][/customml]"));



        //
        // 3. Implement stack that will output n items at once
        //
        WeirdStack<Integer> weirdStack = new WeirdStack<Integer>(2); // will output 2 items at once.
        weirdStack.push(1);
        weirdStack.push(2);
        weirdStack.push(3);

        java.util.List<Integer> popped = weirdStack.pop();
        // PROBLEM: Verify that popped is [3, 2]
        System.out.println("3.1 : " + popped.equals(java.util.Arrays.asList(3, 2)));

        weirdStack.setPopSize(3); // will output 3 items at once.
        weirdStack.push(4);
        weirdStack.push(5);

        List<Integer> poppedAgain = weirdStack.pop();
        // PROBLEM: Verify that poppedAgain is [5, 4, 1]
        System.out.println("3.2 : " + poppedAgain.equals(java.util.Arrays.asList(5, 4, 1)));
    }
}

// PROBLEM 1. Change car engine.
//
class Car {
    public double getMaxSpeed(){
        return 1.5;
    }

    public static void changeEngine(Engine engine){
        engine.getMaxSpeed();
    }
}

interface Engine {
    public double getMaxSpeed();
}

class V6Engine implements Engine {
    @Override
    public double getMaxSpeed(){
        return 1.5;
    }

}

class TurbopropEngine implements Engine {
    @Override
    public double getMaxSpeed() {
        return 1.9;
    }
}


//
// PROBLEM 2. Custom HTML-like markup language.
//
class MarkupElement {
    final List<String> wordBegin = new ArrayList<String>();
    final List<String> wordEnd = new ArrayList<String>();

    String word, word1;
    String content;
    String resultBegin, resultEnd, result;

    public MarkupElement(String s, String s1) {
        this.word = s;
        this.word1 = s1;
        wordBegin.add(word);
        wordEnd.add(word1);
    }

    public void setContent(String s) {
        this.content = s;
    }

    public String produceOutput() {
        int countBegin = wordBegin.size();
        int countEnd = wordEnd.size();

        for(int a=0; a<countBegin; a++){
            if(resultBegin != null) {
                resultBegin = resultBegin + wordBegin.get(a);
            } else {
                resultBegin = wordBegin.get(a);
            }
        }

        for (int a=(countEnd-1); a>=0; a--){
            if(resultEnd != null) {
                resultEnd = resultEnd + wordEnd.get(a);
            } else {
                resultEnd = wordEnd.get(a);
            }
        }
        result = resultBegin + content + resultEnd;
        return result;
    }

    public void addChildren(MarkupElement body) {
        wordBegin.add(body.word);
        wordEnd.add(body.word1);
        setContent("[i]I am italic.[/i]");
    }
}

class RootElement extends MarkupElement {
    public RootElement(String s, String s1) {
        super(s, s1);
    }
}

class BodyElement extends MarkupElement {
    public BodyElement(String s, String s1) {
        super(s, s1);
    }
}
class ItalicElement extends MarkupElement {
    public ItalicElement(String s, String s1){
        super(s, s1);
    }
}

//
// PROBLEM 3. Implement stack that will output n items at once.
//
class WeirdStack<Integer> {
    private List<java.lang.Integer> stack;
    private int sizeList = 0;
    private int popSize = 0;

    public WeirdStack(int i) {
        stack = new ArrayList<java.lang.Integer>(i);
        setPopSize(i);
    }


    public void push(java.lang.Integer i) {
        stack.add(i);
    }

    private void getLengthList(){
        sizeList = stack.size();
    }


    public void setPopSize(int i){
        popSize = i;

    }

    public List<java.lang.Integer> pop() {

        getLengthList();
        List<java.lang.Integer> popstack = new ArrayList<java.lang.Integer>(sizeList);

        for(int a=(sizeList-1); a>((sizeList-1)-popSize); a--) {
            popstack.add(stack.get(a));
            java.lang.Integer x = stack.remove(a);
        }
        return popstack;
    }
}

