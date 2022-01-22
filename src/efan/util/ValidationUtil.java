package efan.util;

import efan.error.ValidationException;
import efan.model.User;

public class ValidationUtil {
    public static void validate(User loginRequest) throws ValidationException{
        if(loginRequest.getUsername().isEmpty()){
            throw new ValidationException("false");
        }
    }
}
