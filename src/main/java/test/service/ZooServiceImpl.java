package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import test.Animal;
import test.Zoo;
import test.dto.Food;
import test.event.HungryEvent;
import test.event.ZooEvent;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZooServiceImpl implements ZooService {
    private final Zoo zoo;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public ZooServiceImpl(Zoo zoo, ApplicationEventPublisher publisher) {
        this.zoo = zoo;
        this.publisher = publisher;
    }

    @Override
    public void feed(Food food) {
        List<Animal> angryAnimals = zoo.getAnimals()
                .stream()
                .peek(animal -> animal.eat(food))
                .filter(Animal::isHungry)
                .collect(Collectors.toList());
        System.out.println(angryAnimals);
    }

    @Override
    public void voice() {
        zoo.getAnimals()
                .stream()
                .filter(Animal::isHungry)
                .forEach(x -> {
                    x.voice();
                    publisher.publishEvent(new HungryEvent(x, "I'm hungry"));
                });

    }
}
