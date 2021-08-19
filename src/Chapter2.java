import java.util.ArrayList;
import java.util.List;

public class Chapter2 {

    interface ApplePredicate {
        boolean test(Apple apple);
    }

    static class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }


    static class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals("green");
        }
    }

    static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) result.add(apple);
        }
        return result;
    }

    interface AppleFormatter {
        String accept(Apple apple);
    }

    /**
     * String formatter for apple.
     * <p>
     * A heavy/light color apple
     */
    static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple apple) {
            return String.format("A %s %s apple", apple.getWeight() > 150 ? "heavy" : "light", apple.getColor());
        }
    }

    static void prettyPrintAppleList(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            System.out.println(formatter.accept(apple));
        }
    }

    static String prettyPrintApple(Apple apple, AppleFormatter formatter) {
        return formatter.accept(apple);
    }

    public static void main(String[] args) {
        List<Apple> greenApples = filterApples(AppleList.appleList, new AppleGreenColorPredicate());
        greenApples.forEach(System.out::println);
        System.out.println("\n");
        List<Apple> redApples = filterApples(AppleList.appleList, (apple -> apple.getColor().equals("red")));
        redApples.forEach(System.out::println);
        System.out.println("\n");
        List<Apple> redAndHeavyApples = filterApples(AppleList.appleList, (apple -> apple.getColor().equals("red") && apple.getWeight() > 150));
        redAndHeavyApples.forEach(apple -> System.out.println(apple + " " + prettyPrintApple(apple, new AppleSimpleFormatter())));
        System.out.println("\n");
        prettyPrintAppleList(AppleList.appleList, new AppleSimpleFormatter());
        new MeaningOfThis().doIt();
        System.out.println("\n");
        List<Apple> filter = filter(greenApples, (apple -> apple.getWeight() > 100));
        filter.forEach(System.out::println);
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) if (p.test(t)) result.add(t);
        return result;
    }

    public static class MeaningOfThis {
        public final int value = 4;

        public void doIt() {
            int value = 6;
            Runnable r = new Runnable() {
                public final int value = 5;

                @Override
                public void run() {
                    int value = 10;
                    System.out.println(this.value + " is in " + Thread.currentThread());
                }
            };
            System.out.println("Calling run");
            new Thread(r).start();
            System.out.println(value);
            r.run();
        }
    }
}
