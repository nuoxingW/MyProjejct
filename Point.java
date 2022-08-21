package Myproject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Point {
 private int num;
 private double x;
 private double y;
public Point(int num, double x, double y) {
	super();
	this.num = num;
	this.x = x;
	this.y = y;
}
public int getNum() {
	return num;
}
public double getX() {
	return x;
}
public double getY() {
	return y;
}
public Point() {
	super();
}
}
