package hctpen;


public class penn {
         
public int ev,x,y;
//int set;
public native int Libload();
public native int Initinstance(int k);//start
public native boolean Initpen(int k);//initialize
public static native penn Pennotify(int k);///Pen events java\jdk1.5.0_05\bin
public native void Destroy(int k);//on close
}