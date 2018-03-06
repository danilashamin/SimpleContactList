package ru.mail.danilashamin.simplecontactlist.view;

import static ru.mail.danilashamin.simplecontactlist.C.MALE_GENDER;

/**
 * Created by Danil on 06.03.2018 on 20:00.
 */

public enum PhotoShapeForm {
    STAR, HEART;

    public static PhotoShapeForm getShapeForm(String gender) {
        if (gender.equals(MALE_GENDER)) {
            return STAR;
        } else return HEART;
    }
}
