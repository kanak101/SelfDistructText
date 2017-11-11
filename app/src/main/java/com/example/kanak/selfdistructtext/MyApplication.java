package com.example.kanak.selfdistructtext;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by kanak on 4/1/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){

        super.onCreate();
        Parse.initialize(this, "1UG3pTBg184gaIDzKNAqfrZnAYiXcW4xn8SSdiJv", "uTZhgB0edmy06OOdpcbriho7DypKsGybPfGZSGL1");


    }
}
