package com.lpp.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String exp = "1+((2+3)*4)-5";
        List<String> strings = toInf(exp);
        System.out.println(parseSuffix(strings));

    }

    public static List<String> parseSuffix(List<String> ls) {

        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for (String item : ls) {

            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.add(item);
            } else if (item.equals(")")) {

                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();

            } else {

                while (s1.size()!=0&&(priority(item)<=priority(s1.peek()))){

                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    public static int priority(String oper) {
        if (oper.equals("*")  || oper.equals("/")  ) {
            return 1;
        } else if (oper.equals("+") || oper.equals("-")) {
            return 0;
        } else {
            return -1;
        }
    }

    public static List<String> toInf(String s) {
        List<String> ls = new ArrayList<>();

        String str;
        int index = 0;
        char ch;

        do {
             ch = s.charAt(index);

            if (ch < 48 || ch > 57) {
                ls.add(ch + "");
                index++;
            } else {
                str = "";
                while (index < s.length() && (ch >= 48 && ch <= 57)) {
                    str=ch+"";
                    if (++index == s.length()) {

                        break;
                    }
                    ch = s.charAt(index);

                }

                ls.add(str);

            }
        }while (index<s.length());

        return ls;
    }
}
