package com.baz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;


/**
 * Hello world!
 */
public class App {

    private static DishDao dishDao = new DishDao();

    public static void main(String[] args) {
        autofillDB();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("0: exit");
                System.out.println("1: add new dish");
                System.out.println("2: get dishes in price range");
                System.out.println("3: get dishes with discount");
                System.out.println("4: get set of dishes under 1kg");
                System.out.print("-> ");
                int menuOption = Integer.parseInt(scanner.nextLine());
                handleInput(menuOption, scanner);
            }
        }
    }

    private static void autofillDB() {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(new Dish("dish1", 100, 250, 0));
            em.persist(new Dish("dish2", 70, 125, 0));
            em.persist(new Dish("dish3", 25, 324, 0));
            em.persist(new Dish("dish4", 200, 123, 10));
            em.persist(new Dish("dish5", 47, 89, 30));
            em.persist(new Dish("dish6", 87, 89, 20));
            em.persist(new Dish("dish7", 65, 500, 50));
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void handleInput(int menuOption, Scanner scanner) {
        switch (menuOption) {
            case 0:
                exitApp();
                break;
            case 1:
                addNewDish(scanner);
                break;
            case 2:
                printDishesInPriceRanges(scanner);
                break;
            case 3:
                printDishesWithDiscount();
                break;
            case 4:
                printDishesUnder1kg();
                break;
        }
    }

    private static void printDishesUnder1kg() {
        List<Dish> allDishes = dishDao.getAll();
        List<Dish> dishesSetUnder1kg = new ArrayList<>();

        int setWeight = 0;
        for (Dish dish : allDishes) {
            if (setWeight + dish.getWeight() < 1000) { // 1 kg
                dishesSetUnder1kg.add(dish);
                setWeight += dish.getWeight();
            }
        }
        System.out.println("set weight: " + setWeight);
        System.out.println(dishesSetUnder1kg);
    }

    private static void printDishesWithDiscount() {
        List<Dish> dishes = dishDao.getWithDiscount();
        for (Dish dish : dishes)
            System.out.println(dish);
    }

    private static void printDishesInPriceRanges(Scanner scanner) {
        System.out.print("min price: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("max price: ");
        int max = Integer.parseInt(scanner.nextLine());
        List<Dish> dishes = dishDao.getInPriceRange(min, max);

        for (Dish dish : dishes)
            System.out.println(dish);
    }

    private static void addNewDish(Scanner scanner) {
        System.out.print("dish name: ");
        String name = scanner.nextLine();
        System.out.print("dish price: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.print("dish weight: ");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.print("discount: ");
        int discount = Integer.parseInt(scanner.nextLine());

        dishDao.addDish(new Dish(name, price, weight, discount));
    }

    private static void exitApp() {
        PersistenceManager.INSTANCE.close();
        System.exit(0);
    }
}
