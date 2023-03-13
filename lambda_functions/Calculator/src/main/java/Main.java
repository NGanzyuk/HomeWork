public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1,1);
        try {
            int c = calc.divide.apply(a, b);
            calc.println.accept(c);
        }catch (ArithmeticException e){
            System.out.println("На ноль делить нельзя!");
        }

    }
}
