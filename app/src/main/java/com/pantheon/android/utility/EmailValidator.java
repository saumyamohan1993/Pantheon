/**
 @Page/Module Name/Class	:	EmailValidator
 @Author Name		:	Mr. Sombir Singh Bisht
 @Date				:	Aug 18,  2015
 @Purpose			:	This page/functionality is used to provide Email Validations.
 */
package com.pantheon.android.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sombirbisht on 18/8/15.
 */
public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param email
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
