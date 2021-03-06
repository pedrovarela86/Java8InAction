import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Chapter1 {

    public static void main(String[] args) {
        AppleList.appleList.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(AppleList.appleList);
        List<Apple> greenApples = FilteringApples.filterApples(AppleList.appleList, FilteringApples::isGreenApple);
        System.out.println("Green apples: " + Arrays.toString(greenApples.toArray()));
        List<Apple> heavyApples = FilteringApples.filterApples(AppleList.appleList, FilteringApples::isHeavyApple);
        System.out.println("Heavy apples: " + Arrays.toString(heavyApples.toArray()));
        List<Apple> yellowApples = FilteringApples.filterApples(AppleList.appleList, apple -> apple.getColor().equals("yellow"));
        System.out.println("Yellow apples: " + Arrays.toString(yellowApples.toArray()));
        System.out.println("Red apples: " + Arrays.toString(AppleList.appleList.stream().filter(apple -> apple.getColor().equals("red")).toArray()));
    }

    @SuppressWarnings("unused")
    public static class FilteringApples {
        public static List<Apple> filterGreenApples(List<Apple> apples) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : apples) {
                if ("green".equals(apple.getColor())) {
                    result.add(apple);
                }
            }
            return result;
        }

        public static List<Apple> filterHeavyApples(List<Apple> apples) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : apples) {
                if (apple.getWeight() > 150) {
                    result.add(apple);
                }
            }
            return result;
        }

        public static boolean isGreenApple(Apple apple) {
            return "green".equals(apple.getColor());
        }

        public static boolean isHeavyApple(Apple apple) {
            return apple.getWeight() > 150;
        }

        public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> appleFunction) {
            List<Apple> result = new ArrayList<>();
            for (Apple apple : apples) {
                if (appleFunction.test(apple)) {
                    result.add(apple);
                }
            }
            return result;
        }
    }
}
