/**
 * Auto Generated Java Class.
 */
package com.example.calorietracker.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private float height;
    private int weight;
    private String name;
    private Boolean sex; // true is for female, false is for male
    private float BMI; //this is the bmi value for user
    private String frequency;//frequency of sports
    private int age;
    private float BMR;
    private String gender;
    private String password;

    public Student(String s, String gender, int age, String frequency, float h, int w, String password) {
        this.height = h;
        this.weight = w;
        this.name = s;
        this.gender = gender;
        this.age = age;
        this.frequency = frequency;
        this.password = password;
    }


    public Student(Parcel in) {
        this.height = in.readFloat();
        this.weight = in.readInt();
        this.name = in.readString();
        this.gender = in.readString();
        this.age = in.readInt();
        this.frequency = in.readString();
    }
//below are setter and getter
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


    public String getFrequency() {
        return this.frequency;
    }

    public float getBMI() {
        this.BMI = this.weight / (this.height * this.height);
        this.BMI = ((float) (Math.round(this.BMI * 10)) / 10);
        return this.BMI;
    }
    public int getAge() {
        return this.age;
    }

    public float getHeight() {

        return this.height;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setName(String s) {
        this.name = s;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void setWeight(int w) {
        this.weight = w;
    }

    public String getGender() {
        return this.gender;
    }

    //calculation for suggested bmr for each meal accroding to formula. getBMRPropotion[0] should be breakfast, ....

    public int[] getBMRPropotion() {
        float bmr = this.getBMR();
        final int[] arr = new int[3];
        if (this.frequency.equals("rarely") || this.frequency.equals("sometimes") || this.frequency.equals("medium")) {
            arr[0] = (int)((float) (Math.round(bmr * 100 * 0.3)) / 100);
            arr[1] = (int)((float) (Math.round(bmr * 100 * 0.4)) / 100);
            arr[2] = (int)((float) (Math.round(bmr * 100 * 0.3)) / 100);
        } else {

            arr[0] = (int)((float) (Math.round(bmr * 100 * 0.4)) / 100);
            arr[1] = (int)((float) (Math.round(bmr * 100 * 0.4)) / 100);
            arr[2] = (int)((float) (Math.round(bmr * 100 * 0.2)) / 100);
        }
        return arr;
    }

    public int getLowerBound(int calorie){
        return calorie - 100;
    }

    public int getUpperBound(int calorie){
        return calorie + 100;
    }



    public float getBMR() {
        float bmr = 1f;
        float value;
        switch (this.getFrequency().toLowerCase()) {
            case "rarely":
                value = 1.2f;
                break;
            case "sometimes":
                value = 1.375f;
                break;
            case "medium":
                value = 1.55f;
                break;
            case "often":
                value = 1.725f;
                break;
            case "always":
                value = 1.9f;
                break;
            default:
                value = 1;
        }

        if (this.gender.equals("Female")) {
            bmr = 655.1f + (this.weight * 9.6f + 1.8f * this.height) - 4.7f * this.age;
        } else {
            bmr = 66.47f + (this.weight * 13.7f + 5f * this.height) - 6.8f * this.age;
        }
        this.BMR = ((float) (Math.round(bmr * 10 * value)) / 10);
        return this.BMR;
    }

    //return a string that describe the bmi, compared with the standard
    public String getBmiString(){
        if(this.getBMI()<18.5f){
            return "Lower than standard";
        }
        else if(this.getBMI()>=18.5f && this.getBMI()<=24.9f){
            return "Perfectly standardized!";
        }
        else if(this.getBMI()>24.9f&&this.getBMI()<27.0f){
            return "A little higher than standard";
        }
        else{
            return "Higher than standard";
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeString(this.name);
        dest.writeString(this.frequency);
        dest.writeInt(this.weight);
        dest.writeFloat(this.height);
        dest.writeFloat(this.BMI);
        dest.writeFloat(this.BMR);
        dest.writeInt(this.age);
    }

}
