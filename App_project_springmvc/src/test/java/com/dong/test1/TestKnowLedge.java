package com.dong.test1;

public class TestKnowLedge {

    public static void main(String[] args) {
        System.out.println("----测试开始-----");
        String aa = "AA";
        String bb = "BB";
        aa = "XX";
        System.out.println(aa);
        final String str = "FF";//基本数据类型和引用数据类型不可变,引用的对象内容可以变
//        str = "KK";
        final Student  student = new Student("SSD");
//          student = new Student("SSD");

        Student  student1 = new Student("RRR");
        final  Student  student2 = student1;
        System.out.println(student2.getName());
        student1.setName("zhangshan");
        System.out.println(student2.getName());

    }
}

class Student{

    String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}