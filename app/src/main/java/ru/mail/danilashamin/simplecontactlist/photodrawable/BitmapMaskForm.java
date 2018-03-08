package ru.mail.danilashamin.simplecontactlist.photodrawable;

import static ru.mail.danilashamin.simplecontactlist.C.MALE_GENDER;

/**
 * Created by Danil on 06.03.2018 on 20:00.
 */

public enum BitmapMaskForm {
    STAR, HEART;

    public static BitmapMaskForm getMaskForm(String gender) {
        if (gender.equals(MALE_GENDER)) {
            return STAR;
        } else return HEART;
    }
}
