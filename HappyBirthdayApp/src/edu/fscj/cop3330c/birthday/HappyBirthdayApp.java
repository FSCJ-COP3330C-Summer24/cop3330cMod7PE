// HappyBirthdayApp.java
// D. Singletary
// 1/29/23
// wish multiple users a happy birthday

package edu.fscj.cop3330c.birthday;

import edu.fscj.cop3330c.dispatch.Dispatcher;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

// main application class
public class HappyBirthdayApp implements BirthdayCardSender {
    //private User user;
    private ArrayList<User> birthdays = new ArrayList<>();
    // Use a Queue<LinkedList> to act as message queue for the dispatcher
    private Queue<BirthdayCard> queue = new LinkedList<BirthdayCard>();
    private Stream<BirthdayCard> stream = queue.stream();

    private static HappyBirthdayApp hba = new HappyBirthdayApp();

    private HappyBirthdayApp() { }

    public static HappyBirthdayApp getApp() {
        return hba;
    }

    // send the card
    public void sendCard(BirthdayCard bc) {
        Dispatcher<BirthdayCard> d = (c)-> {
            this.queue.add(c);
        };
        d.dispatch(bc);
    }

    // compare current month and day to user's data
    // to see if it is their birthday
    public boolean isBirthday(User u) {
        boolean result = false;

        LocalDate today = LocalDate.now();
        if (today.getMonth() == u.getBirthday().getMonth() &&
                today.getDayOfMonth() == u.getBirthday().getDayOfMonth())
            result = true;

        return result;
    }

    // add multiple birthdays
    public void addBirthdays(User... users) {
        for (User u : users) {
            birthdays.add(u);
        }
    }

    // main program
    public static void main(String[] args) {

        BirthdayCardFactory cardFactory;

        // use current date for testing, adjust where necessary
        ZonedDateTime currentDate = ZonedDateTime.now();

        final User[] USERS = {
            // negative test
            new User("Miles", "Bennell", "Miles.Bennell@email.test",
                currentDate.minusDays(1)),
            // positive tests
            // test with odd length full name
            new User("Becky", "Driscoll", "Becky.Driscoll@email.test",
                currentDate),
            // test with even length full name
            new User("Jack", "Belicec", "Jack.Belicec@email.test",
                currentDate),
            new User("Theodora", "Belicec", "Theodora.Belicec.@email.test",
                currentDate),
            new User("Sally", "Withers", "Sally.Withers@email.test",
                currentDate)
        };

        hba.addBirthdays(USERS);

        // show the birthdays
        if (!hba.birthdays.isEmpty()) {
            System.out.println("Here are the birthdays:");
            int count = 0;
            for (User u : hba.birthdays) {
                // see if today is their birthday
                if (!hba.isBirthday(u))
                   System.out.println("Sorry, today is not their birthday.\n");
                else {
                    // create a lambda to send the card
                    BirthdayCardSender sender = (bc) -> {
                        hba.sendCard(bc);
                    };

                    BirthdayCard card = null;
                    count++;

                    // alternate between text and graphics card factories
                    cardFactory = (count % 2 == 0) ?
                            new BirthdayCardFactoryText() :
                            new BirthdayCardFactoryImage();

                    // FIB 6 Add statements for values 2, 3, and 4 of the
                    // variable count in the following switch statement
                    // and call the appropriate method to create the
                    // age-specific cards described in the comment
                    // (do not remove these comment lines)
                    switch (count) {
                        case 1:
                            // create a card for child
                            card = cardFactory.createCardChild(u);
                            break;
                        case 2:
                            // create a card for an adolescent
                            card = cardFactory.createCardAdolescent(u);
                            break;
                        case 3:
                            // create a card for an adult
                            card = cardFactory.createCardAdult(u);
                            break;
                        case 4:
                            // create a card for a senior
                            card = cardFactory.createCardSenior(u);
                            break;
                    }

                    sender.sendCard(card);
                }
            }
        }
        // process the stream
        System.out.println("starting forEach");
        hba.stream.forEach(System.out::print);
    }
}

// user class
class User {
    private StringBuilder name;
    private String email;
    private ZonedDateTime birthday;

    public User(String fName, String lName, String email, 
                ZonedDateTime birthday) {
        this.name = new StringBuilder();
        this.name.append(fName).append(" ").append(lName);
        this.email = email;
        this.birthday = birthday;
    }

    public StringBuilder getName() {
        return name;
    }

    public String getEmail() { return email; }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return this.name + "," + this.birthday;
    }
}
