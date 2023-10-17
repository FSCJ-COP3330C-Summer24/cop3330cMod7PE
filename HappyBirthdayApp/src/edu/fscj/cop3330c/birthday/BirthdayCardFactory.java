// BirthdayCardFactory.java
// D. Singletary
// 7/2/23
// Birthday card factories

package edu.fscj.cop3330c.birthday;

import edu.fscj.cop3330c.image.ImageType;

public abstract class BirthdayCardFactory {
    public abstract BirthdayCard createCardChild(User u);
    // FIB 3 Add the remaining abstract methods for  
    // adolescents, adults, and seniors after this comment
    // (do not remove these comment lines)
    public abstract BirthdayCard createCardAdolescent(User u);
    public abstract BirthdayCard createCardAdult(User u);
    public abstract BirthdayCard createCardSenior(User u);
}

class BirthdayCardFactoryText extends BirthdayCardFactory {
    public BirthdayCard createCardChild(User u) {
        return new BirthdayCard_ChildText(u);
    }
    public BirthdayCard createCardAdolescent(User u) {
        return new BirthdayCard_AdolescentText(u);
    }
    public BirthdayCard createCardAdult(User u) {
        return new BirthdayCard_AdultText(u);
    }
    public BirthdayCard createCardSenior(User u) {
        return new BirthdayCard_SeniorText(u);
    }
}

class BirthdayCardFactoryImage extends BirthdayCardFactory {

    public static String getImageFile(ImageType type,
                                      String ageStr) {
        String fileName = "";
        if (type != ImageType.NONE)
            fileName = "bdc-" + ageStr +
                       ImageType.getFileExtension(ImageType.JPG);
        else {
            System.err.println("error - invalid file spec");
        }
        return fileName;
    }

    public BirthdayCard createCardChild(User u) {
        return new BirthdayCard_ChildImage(u);
    }
    // FIB 5 Add the remaining 3 method implementations for
    // adolescents, adults, and seniors after this comment
    // (do not remove these comment lines)
    public BirthdayCard createCardAdolescent(User u) {
        return new BirthdayCard_AdolescentImage(u);
    }
    public BirthdayCard createCardAdult(User u) {
        return new BirthdayCard_AdultImage(u);
    }
    public BirthdayCard createCardSenior(User u) {
        return new BirthdayCard_SeniorImage(u);
    }
}