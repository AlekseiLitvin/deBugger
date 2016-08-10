package litvin.controllers;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
    public static String getPathVariable(HttpServletRequest req){
        final String DELIMITER = "/";
        final int PATH_VARIABLE_INDEX = 1;
        String path = req.getPathInfo();
        return path.split(DELIMITER)[PATH_VARIABLE_INDEX];
    }
}
