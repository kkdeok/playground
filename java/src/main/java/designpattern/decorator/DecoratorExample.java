package designpattern.decorator;

interface Coffee {
    String getDescription();
    int getCost();
}

class BasicCoffee implements Coffee {
    @Override
    public int getCost() {
        return 3000;
    }

    @Override
    public String getDescription() {
        return "Basic Coffee";
    }
}

// Decorate 추상 클래스 (핵심)
//   추상 클래스는 인터페이스의 미구현 메서드를 남겨둘 수 있다.
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee; // composition

    CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 500;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", milk";
    }
}

class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", whip";
    }

    public int getCost() {
        return coffee.getCost() + 700;
    }
}


public class DecoratorExample {
    public static void main(String[] args) {
        Coffee c = new BasicCoffee();

        c = new MilkDecorator(c);
        c = new WhipDecorator(c);

        System.out.println(c.getDescription());
        System.out.println(c.getCost());
    }
    
}
