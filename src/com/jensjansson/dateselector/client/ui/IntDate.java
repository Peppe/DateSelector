package com.jensjansson.dateselector.client.ui;

public class IntDate {
    private int day;
    private int month;
    private int year;

    public IntDate() {
        day = -1;
        month = -1;
        year = -1;
    }

    public IntDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) {
            return true;
        }
        if (!(aThat instanceof IntDate)) {
            return false;
        }
        IntDate that = (IntDate) aThat;
        return day == that.day && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + day;
        hashCode = 31 * hashCode + month;
        hashCode = 31 * hashCode + year;
        return hashCode;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }
}