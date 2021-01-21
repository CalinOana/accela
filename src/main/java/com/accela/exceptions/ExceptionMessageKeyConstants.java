package com.accela.exceptions;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ExceptionMessageKeyConstants {

    public static final String INEXISTENT_PERSON = "person.inexistent";
    public static final String INEXISTENT_ADDRESS = "address.inexistent";


    public static final String NULL_ID = "null.id";
    public static final String NULL_PERSON = "null.person";
    public static final String NULL_PERSON_FIRST_NAME = "null.person.first.name";
    public static final String NULL_PERSON_LAST_NAME = "null.person.last.name";

    public static final String NULL_ADDRESS= "null.address";
    public static final String NULL_ADDRESS_STREET= "null.address.street";
    public static final String NULL_ADDRESS_CITY= "null.address.city";
    public static final String NULL_ADDRESS_STATE= "null.address.state";
    public static final String NULL_ADDRESS_POSTAL_CODE= "null.address.postal.code";

}
