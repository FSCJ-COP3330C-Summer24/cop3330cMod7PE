// BirthdayCard.java
// D. Singletary
// 7/2/23
// Birthday cards

package edu.fscj.cop3330c.birthday;

import edu.fscj.cop3330c.image.*;
import java.util.Arrays;

public abstract class BirthdayCard implements BirthdayCardBuilder {

    // test with odd length (comment to test with even length, below)
    protected final String WISHES =
            "Hope all of your birthday wishes come true!";
    // uncomment to test with even length
    //final String WISHES = "Hope all of your birthday wishes come true!x";

    private User user;
    private String message;
    private Image cardImage;

    public BirthdayCard() { }

    public BirthdayCard(User user, String msg) {
        this.user = user;
        //System.out.println( // DEBUG
        //        "building card for " + this.getClass().getSimpleName());
        this.buildCard(user, msg);
    }

    public BirthdayCard(User user, Image cardImage) {
        this.user = user;
        this.cardImage = cardImage;
        //System.out.println( // DEBUG
        //        "building card for " + this.getClass().getSimpleName());
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    // given a String containing a (possibly) multi-line message,
    // split the lines, find the longest line, and return its length
    public int getLongest(String s) {
        final String NEWLINE = "\n";
        int maxLength = 0;
        String[] splitStr = s.split(NEWLINE);
        for (String line : splitStr)
            if (line.length() > maxLength)
                maxLength = line.length();
        return maxLength;
    }

    public Image getCardImage() {
        return this.cardImage;
    }

    public void setCardImage(Image cardImage) {
        this.cardImage = cardImage;
    }

    public void buildCard(User u, String message) {

        String msg;
        if (message == null)
            msg = "Happy Birthday, " + u.getName() + "\n" + WISHES;
        else
            msg = message;

        final String NEWLINE = "\n";

        // get the widest line and number of lines in the message
        int longest = getLongest(msg);

        // need to center lines
        // dashes on top (header) and bottom (footer)
        // vertical bars on the sides
        // |-----------------------|
        // | longest line in group |
        // |      other lines      |
        // |-----------------------|
        //
        // pad with an extra space if the length is odd

        int numDashes = (longest + 2) + (longest % 2);  // pad if odd length
        char[] dashes = new char[numDashes];  // header and footer
        char[] spaces = new char[numDashes];  // body lines
        Arrays.fill(dashes, '-');
        Arrays.fill(spaces, ' ');
        String headerFooter = "|" + new String(dashes) + "|\n";
        String spacesStr = "|" + new String(spaces) + "|\n";

        // start the card with the header
        this.message  = headerFooter;

        // split the message into separate strings
        String[] splitStr = msg.split(NEWLINE);
        for (String s : splitStr) {
            String line = spacesStr;  // start with all spaces

            // create a StringBuilder with all spaces,
            // then replace some spaces with the centered string
            StringBuilder buildLine = new StringBuilder(spacesStr);

            // start at  middle minus half the length of the string (0-based)
            int start = (spacesStr.length() / 2) - (s.length() / 2);
            // end at the starting index plus the length of the string
            int end = start + s.length();
            /// replace the spaces and create a String, then append
            buildLine.replace(start, end, s);
            line = new String(buildLine);
            this.message += line;
        }
        // append the footer
        this.message += headerFooter;
    }

    @Override
    public String toString() {
        String s = "Birthday card for " + this.getUser().getName() + "\n";
        s += (this.cardImage != null) ?
                (this.cardImage + "\n") :
                getMessage();
        return s;
    }
}

class BirthdayCard_ChildText extends BirthdayCard {
    private static final String MSG =
            """
            Happy Birthday! Hope your day is filled with magic, fun,
            and all your birthday wishes coming true! 🎈🎂
            """;
    public BirthdayCard_ChildText(User u) {
        super(u, MSG);
    }
}

// FIB 1 Add the remaining 3 text-based BirthdayCard subclasses for 
// adolescents, adults, and seniors after this comment
// (do not remove these comment lines)
class BirthdayCard_AdolescentText extends BirthdayCard {
    private static final String MSG =
            """
            Happy Birthday! Hope your day is as awesome as you are
            and all your birthday wishes come true! 🎉🎈
            """;
    public BirthdayCard_AdolescentText(User u) {
        super(u, MSG);
    }
}

class BirthdayCard_AdultText extends BirthdayCard {
    private static final String MSG =
            """
            Happy Birthday!
            Here's to a wonderful year ahead, filled with joy, growth,
            and every single one of your birthday wishes coming true.
            Cheers!
            """;
    public BirthdayCard_AdultText(User u) {
        super(u, MSG);
    }
}

class BirthdayCard_SeniorText extends BirthdayCard {
    private static final String MSG =
            """
            Happy Birthday!
            May this special day bring you cherished memories, joy
            in the present, and hopes for the future.
            Wishing all of your birthday dreams and wishes come true!
            """;
    public BirthdayCard_SeniorText(User u) {
        super(u, MSG);
    }
}

class BirthdayCard_ChildImage extends BirthdayCard {
    private static final Image IMAGE = new Image(
            BirthdayCardFactoryImage.getImageFile(
                    ImageType.JPG, "child"),
            ImageType.JPG);
    public BirthdayCard_ChildImage(User u) {
        super(u, IMAGE);
    }
}

// FIB 2 Add the remaining 3 image-based BirthdayCard subclasses for 
// adolescents, adults, and seniors after this comment
// (do not remove these comment lines)
class BirthdayCard_AdolescentImage extends BirthdayCard {
    private static final Image IMAGE = new Image(
            BirthdayCardFactoryImage.getImageFile(
                    ImageType.JPG, "adolescent"),
            ImageType.JPG);
    public BirthdayCard_AdolescentImage(User u) {
        super(u, IMAGE);
    }
}

class BirthdayCard_AdultImage extends BirthdayCard {
    private static final Image IMAGE = new Image(
            BirthdayCardFactoryImage.getImageFile(
                    ImageType.JPG, "adult"),
            ImageType.JPG);
    public BirthdayCard_AdultImage(User u) {
        super(u, IMAGE);
    }
}

class BirthdayCard_SeniorImage extends BirthdayCard {
    private static final Image IMAGE = new Image(
            BirthdayCardFactoryImage.getImageFile(
                    ImageType.JPG, "senior"),
            ImageType.JPG);
    public BirthdayCard_SeniorImage(User u) {
        super(u, IMAGE);
    }
}
