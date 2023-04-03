package org.firstinspires.ftc.teamcode.Utils;

public class ButtonEX {

    private final String name;

    private boolean isBool;

    private boolean press;
    private boolean held;
    private boolean release;

    public ButtonEX(String name, boolean isBool){
        this.name = name;
        this.isBool = isBool;
    }

    public String getName(){
        return name;
    }

    public boolean isPress(){
        return press;
    }
    public boolean isHeld(){
        return held;
    }
    public boolean isRelease(){
        return release;
    }

    public void setPress(boolean b){
        press=b;
    }
    public void setHeld(boolean b){
        held=b;
    }
    public void setRelease(boolean b){
        release=b;
    }

}
