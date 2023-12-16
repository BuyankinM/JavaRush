package com.javarush.task.task25.task2515;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Space {
    private int height;
    private int width;
    private SpaceShip ship;
    private List<Ufo> ufos = new ArrayList<>();
    private List<Rocket> rockets = new ArrayList<>();
    private List<Bomb> bombs = new ArrayList<>();

    public static Space game;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public List<BaseObject> getAllItems() {
        List<BaseObject> allObjects = new ArrayList<>();
        allObjects.add(ship);
        allObjects.addAll(bombs);
        allObjects.addAll(rockets);
        allObjects.addAll(ufos);
        return allObjects;
    }

    public void moveAllItems() {
        for (BaseObject o : getAllItems()) {
            o.move();
        }
    }

    public Space(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void run() {
        //Создаем холст для отрисовки.
        Canvas canvas = new Canvas(width, height);

        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Игра работает, пока корабль жив
        while (ship.isAlive()) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если "стрелка влево" - сдвинуть фигурку влево
                System.out.print(event.getKeyCode());
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    ship.moveLeft();
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    ship.moveRight();
                    //Если "пробел" - стреляем
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ship.fire();
            }

            //двигаем все объекты игры
            moveAllItems();

            //проверяем столкновения
            checkBombs();
            checkRockets();
            //удаляем умершие объекты из списков
            removeDead();

            //Создаем НЛО (1 раз в 10 ходов)
            createUfo();

            //Отрисовываем все объекты на холст, а холст выводим на экран
            canvas.clear();
            draw(canvas);
            canvas.print();

            //Пауза 300 миллисекунд
            Space.sleep(300);
        }

        //Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }
    public void draw(Canvas canvas) {
        //draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }

        for (BaseObject object : getAllItems()) {
            object.draw(canvas);
        }
    }

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) throws Exception {
        game = new Space(100, 100);
        game.setShip(new SpaceShip(10, 18));
        game.run();
    }

    public void createUfo() {
        if (!ufos.isEmpty()) return;
        ufos.add(new Ufo(width / 2, 0));
    }

    public void checkBombs() {
        for (Bomb bomb : bombs) {
            if (bomb.isIntersect(ship)) {
                bomb.die();
                ship.die();
            } else if (bomb.y > height) {
                bomb.die();
            }
        }
    }

    public void checkRockets() {
        for (Rocket rocket : rockets) {
            for (Ufo ufo : ufos) {
                if (rocket.isIntersect(ufo)) {
                    ufo.die();
                    rocket.die();
                } else if (rocket.y < 0) {
                    rocket.die();
                }
            }
        }
    }

    public void removeDead() {
        rockets.removeIf(r -> !r.isAlive());
        bombs.removeIf(b -> !b.isAlive());
        ufos.removeIf(u -> !u.isAlive());
    }
}
