//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public  class ClassesAndObjects {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.name = "Александр";
        person1.age = 22;
        Person person2 = new Person();
        person2.name = "Чепух";
        person2.age = 30;
        int year1 = person1.calculateYearsToRetirement();
        int year2 = person2.calculateYearsToRetirement();
        System.out.println("Первому человеку до пенсии: "+year1+" лет");
        System.out.println("Первому человеку до пенсии: "+year2+" лет");
    }
}

class Person{
    String name;
    int age;

    int calculateYearsToRetirement(){
        int years = 70-age;
        return years;
    }

    void speak(){
        for(int i = 0; i<3; i++){
        System.out.println("Меня зовут "+ name +",мне "+age+" лет");}
    }
    void sayHello(){
        System.out.println("Привет !");
    }
}