package org.tensorflow.lite.examples.detection;

import static android.util.Log.ERROR;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class TextToSpeech {
    private final android.speech.tts.TextToSpeech tts;
    private final double border_Left=(double)212;
    private final double border_Lower=(double)212;
    private final double border_Right=(double)426;
    private final double border_Top=(double)426;


    public TextToSpeech(Context context) {
        //TTS 생성후, OnInitListener로 초기화
        tts = new android.speech.tts.TextToSpeech(context, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != ERROR){
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
    }

    public void readText(String text) {
        tts.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, text);
        tts.playSilence(10000, android.speech.tts.TextToSpeech.QUEUE_ADD,null);

    }
    public void readDelay() {
        tts.speak("하이요", android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
        tts.playSilence(1000, android.speech.tts.TextToSpeech.QUEUE_ADD,null);
        //tts.speak("바이요", android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);

    }

    public void readGPs(String text){
        tts.speak("현재 위치는"+ text +"입니다.", android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);

    }

    public String inputLocation(ArrayList<Double> location) {
        ArrayList<Double> i= location;

        double x_median=i.get(0);
        double y_median=i.get(1);
        double height=i.get(2);
        double width=i.get(3);

        System.out.println("test:"+i.get(0));
        System.out.println("test:"+i.get(1));
        System.out.println("test:"+i.get(2));
        System.out.println("test:"+i.get(3));

        String Temp="";

        if(x_median< border_Left) { //x 중앙값이 왼쪽 경계에 있을때

            if(y_median <border_Lower) {
                Temp = "왼쪽 하단";
            }
            else {
                Temp= (y_median >= border_Lower && y_median < border_Top) ? "왼쪽":"왼쪽 상단";
            }
        }
        else if(x_median >= border_Left && x_median< border_Right ) { //x 중앙값이 중앙 경계에 있을때

            if (y_median < border_Lower) {
                Temp = "중앙 하단";
            } else {
                Temp = (y_median >= border_Lower && y_median < border_Top) ? "중앙" : "중앙 상단";
            }
        }
        else if(x_median > border_Right){
            if (y_median < border_Lower) {
                Temp = "오른쪽 하단";
            } else {
                Temp = (y_median >= border_Lower && y_median < border_Top) ? "오른쪽" : "오른쪽 상단";
            }
        }
        else System.out.println("label value error");

        return Temp;
    }

}
