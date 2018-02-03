package com.caixnet.forone;

import android.util.Log;

//import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caixnet on 12/22/17.
 */


public class ChaiCode{
    // define ChaiCode variable
    Map<String, String> chaicodes;
    public ChaiCode(){
        /* define chai Code that code reserved */

        chaicodes = new HashMap<String, String>();
        // set ChaiCode
        chaicodes.put("0320", "a");
        chaicodes.put("0230", "a");
        chaicodes.put("03202", " and ");
        chaicodes.put("02303", " and ");
        chaicodes.put("012032", "b");
        chaicodes.put("023012", "b");
        chaicodes.put("032012", "b");
        chaicodes.put("032102", "b");
        chaicodes.put("021032", "b");
        chaicodes.put("012302","b");
        chaicodes.put("212", "b");
        chaicodes.put("2121", " break\n");
        chaicodes.put("0132", "c");
        chaicodes.put("01323","class ");
        chaicodes.put("013231"," continue ");
        chaicodes.put("0120", "d");
        chaicodes.put("0210", "d");
        chaicodes.put("01202", "def (),");
        chaicodes.put("02101", "def (),");
        chaicodes.put("012021", "del ");
        chaicodes.put("021012", "del ");
        chaicodes.put("01321", "e");
        chaicodes.put("01231", "e");
        chaicodes.put("013212", "else,");
        chaicodes.put("012313", "else,");
        chaicodes.put("0132123", "except ,");
        chaicodes.put("0123132", "except ,");
        chaicodes.put("02312", "f");
        chaicodes.put("02132", "f");
        chaicodes.put("023121", "if ,");
        chaicodes.put("021323", "if ,");
        chaicodes.put("0231212", "elif ,");
        chaicodes.put("0213232", "elif ,");
        chaicodes.put("0231213", "for in ,");
        chaicodes.put("0213231", "for in ,");
        chaicodes.put("02312132", "finally ");
        chaicodes.put("02132312", "finally ");
        chaicodes.put("013", "g");
        chaicodes.put("0131", "global ");
        chaicodes.put("02130", "h");
        chaicodes.put("03120", "h");
        chaicodes.put("03", "i");
        chaicodes.put("032", "j");
        chaicodes.put("012", "k");
        chaicodes.put("023", "l");
        chaicodes.put("0232", "lambda ");
        chaicodes.put("021031", "m");
        chaicodes.put("031021", "m");
        chaicodes.put("012031", "m");
        chaicodes.put("013021", "m");
        chaicodes.put("021301","m");
        chaicodes.put("031201","m");
        chaicodes.put("303", "m");
        chaicodes.put("3030", "import ");
        chaicodes.put("0213", "n");
        chaicodes.put("02131", "not");
        chaicodes.put("021312", "in");
        chaicodes.put("01320", "o");
        chaicodes.put("02310", "o");
        chaicodes.put("0132030", " or ");
        chaicodes.put("0231030", " or ");
        chaicodes.put("01302", "p");
        chaicodes.put("03102", "p");
        chaicodes.put("013020", "print()");
        chaicodes.put("031020", "print()");
        chaicodes.put("0130203", "pass\n");
        chaicodes.put("0130201", "pass\n");
        chaicodes.put("0310201", "pass\n");
        chaicodes.put("0310203", "pass\n");
        chaicodes.put("02103", "q");
        chaicodes.put("01203", "q");
        chaicodes.put("013203", "r");
        chaicodes.put("031023", "r");
        chaicodes.put("032013", "r");
        chaicodes.put("023013", "r");
        chaicodes.put("023103", "r");
        chaicodes.put("013023","r");
        chaicodes.put("030", "r");
        chaicodes.put("0303", "raise");
        chaicodes.put("03030", "return ");
        chaicodes.put("01230", "s");
        chaicodes.put("03210", "s");
        chaicodes.put("012303", "is");
        chaicodes.put("032101", "is");
        chaicodes.put("0123032", "self");
        chaicodes.put("0321012", "self");
        chaicodes.put("0130", "t");
        chaicodes.put("0310", "t");
        chaicodes.put("01303", "try ,");
        chaicodes.put("03101", "try ,");
        chaicodes.put("0231", "u");
        chaicodes.put("021", "v");
        chaicodes.put("0321", "w");
        chaicodes.put("03212", "while ,");
        chaicodes.put("032123", " with ");
        chaicodes.put("0312", "x");
        chaicodes.put("031", "y");
        chaicodes.put("0313", "yield ");
        chaicodes.put("0123", "z");
        chaicodes.put("12", "1");
        chaicodes.put("1032", "2");
        chaicodes.put("1023", "3");
        chaicodes.put("123", "4");
        chaicodes.put("1231", "5");
        chaicodes.put("1321", "5");
        chaicodes.put("10230", "6");
        chaicodes.put("10320", "6");
        chaicodes.put("13203", "6");
        chaicodes.put("13023", "6");
        chaicodes.put("102", "7");
        chaicodes.put("103", "7");
        chaicodes.put("1203", "8");
        chaicodes.put("10213", "9");
        chaicodes.put("12013", "9");
        chaicodes.put("102312", "0");
        chaicodes.put("120132", "0");
        chaicodes.put("123102", "0");
        chaicodes.put("132012", "0");
        chaicodes.put("102132", "0");
        chaicodes.put("10231", "0");
        chaicodes.put("13201", "0");
        chaicodes.put("d0320", "A");
        chaicodes.put("d0230", "A");
        chaicodes.put("d012032", "B");
        chaicodes.put("d023012", "B");
        chaicodes.put("d032012", "B");
        chaicodes.put("d032102", "B");
        chaicodes.put("d021032", "B");
        chaicodes.put("d012302","B");
        chaicodes.put("d0132", "C");
        chaicodes.put("d2310", "");
        chaicodes.put("d0120", "D");
        chaicodes.put("d0210", "D");
        chaicodes.put("d01321", "E");
        chaicodes.put("d01231", "E");
        chaicodes.put("d02312", "F");
        chaicodes.put("d02132", "F");
        chaicodes.put("d013", "G");
        chaicodes.put("d02130", "H");
        chaicodes.put("d03120", "H");
        chaicodes.put("d03", "I");
        chaicodes.put("d032", "J");
        chaicodes.put("d012", "K");
        chaicodes.put("d023", "L");
        chaicodes.put("d021031", "M");
        chaicodes.put("d031021", "M");
        chaicodes.put("d012031", "M");
        chaicodes.put("d013021", "M");
        chaicodes.put("d021301","M");
        chaicodes.put("d031201","M");
        chaicodes.put("d0213", "N");
        chaicodes.put("d01320", "O");
        chaicodes.put("d02310", "O");
        chaicodes.put("d01302", "P");
        chaicodes.put("d03102", "P");
        chaicodes.put("d02103", "Q");
        chaicodes.put("d01203", "Q");
        chaicodes.put("d013203", "R");
        chaicodes.put("d031023", "R");
        chaicodes.put("d032013", "R");
        chaicodes.put("d023013", "R");
        chaicodes.put("d023103", "R");
        chaicodes.put("d013023","R");
        chaicodes.put("d030", "R");
        chaicodes.put("d01230", "S");
        chaicodes.put("d03210", "S");
        chaicodes.put("d0130", "T");
        chaicodes.put("d0310", "T");
        chaicodes.put("d0231", "U");
        chaicodes.put("d021", "V");
        chaicodes.put("d0321", "W");
        chaicodes.put("d0312", "X");
        chaicodes.put("d031", "Y");
        chaicodes.put("d0123", "Z");
        chaicodes.put("23", "CHAI_CURSOR_RIGHT");
        chaicodes.put("010", "CHAI_CURSOR_LEFT");
        chaicodes.put("232", "");
        chaicodes.put("10", "-");
        chaicodes.put("101", "+");
        chaicodes.put("32", "_");
        chaicodes.put("323", "=");
        chaicodes.put("3232", "__");
        chaicodes.put("32323", "==");
        chaicodes.put("121", "+");
        chaicodes.put("1010", "+");
        chaicodes.put("2130", "");
        chaicodes.put("2103", "");
        chaicodes.put("01", " ");
        chaicodes.put("213", "\t");
        chaicodes.put("13", "|");
        chaicodes.put("21", "/");
        chaicodes.put("30", "\\");
        chaicodes.put("31", "!");
        chaicodes.put("31323", "!=");
        chaicodes.put("3203", "@");
        chaicodes.put("3023", "@");
        chaicodes.put("32013", "#");
        chaicodes.put("31023", "#");
        chaicodes.put("2301", "$");
        chaicodes.put("3210", "$");
        chaicodes.put("3213", "%");
        chaicodes.put("3123", "%");
        chaicodes.put("302", "^");
        chaicodes.put("203", "^");
        chaicodes.put("32103", "&");
        chaicodes.put("30123", "&");
        chaicodes.put("3012", "&");
        chaicodes.put("3021", "*");
        chaicodes.put("30212", "**");
        chaicodes.put("2031", "~");
        chaicodes.put("1302", "`");
        chaicodes.put("301", "`");
        chaicodes.put("312", "`");
        chaicodes.put("02", "'");
        chaicodes.put("20", "\"");
        chaicodes.put("202", ";");
        chaicodes.put("313", ":");
        chaicodes.put("020", ",");
        chaicodes.put("131", ".");
        chaicodes.put("320", "(");
        chaicodes.put("231", ")");
        chaicodes.put("3202", "()");
        chaicodes.put("2313", "()");
        chaicodes.put("3201", "[");
        chaicodes.put("2310", "]");
        chaicodes.put("32010", "[]");
        chaicodes.put("23101", "[]");
        chaicodes.put("230", ">");
        chaicodes.put("321", "<");
        chaicodes.put("2303", "<>");
        chaicodes.put("3212", "<>");
        chaicodes.put("32012", "{");
        chaicodes.put("32102", "{");
        chaicodes.put("21023", "{");
        chaicodes.put("20123", "{");
        chaicodes.put("30132", "}");
        chaicodes.put("31032", "}");
        chaicodes.put("23103", "}");
        chaicodes.put("23013", "}");
        chaicodes.put("320121", "{}");
        chaicodes.put("321020", "{}");
        chaicodes.put("210232", "{}");
        chaicodes.put("201232", "{}");
        chaicodes.put("301323", "{}");
        chaicodes.put("310323", "{}");
        chaicodes.put("231030", "{}");
        chaicodes.put("230131", "{}");
        chaicodes.put("310", "?");
        chaicodes.put("3101", "? ,");
        chaicodes.put("20312","Hello, World!");

    }
    // get chaicode value
    public String get(String code){
        String cai= "";
        try{
            if(chaicodes.containsKey(code))
                cai = chaicodes.get(""+code);
        } catch (Exception e){
            Log.e("CAI","no code mapping");
            cai = "";
        }
        return cai;
    }
    // queue data make to code
    public String makeCode(String str){
        if(str==null) return "";
        if(str.length()<2) {return str;}
        StringBuffer sb = new StringBuffer();
        sb.append(str.charAt(0));
        for(int i = 1; i < str.length()-1; i++){
            if(str.charAt(i-1) != str.charAt(i))
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }


}
