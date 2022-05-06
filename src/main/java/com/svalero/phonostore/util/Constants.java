package com.svalero.phonostore.util;

import java.util.regex.Pattern;

public class Constants {
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/library";
    public static final String ORACLE_URL = "jdbc:oracle:thin:@//localhost:1521/xe";

    public static final String USERNAME = "TIENDA";
    public static final String PASSWORD = "1234";

    public static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
    public static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    public static final String[] INVALIDOS = new String[] { "00000000T", "00000001R", "99999999R" };

}
