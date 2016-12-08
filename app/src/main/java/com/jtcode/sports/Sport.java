package com.jtcode.sports;

public class Sport {
    private String name;
    private int img;
    private boolean checked;

    public Sport(String name, int img, boolean checked) {
        this.name = name;
        this.img = img;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
