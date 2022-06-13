package pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions;

public class UserIsAlreadyEmployeeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "User id %s is already an Employee";

    public UserIsAlreadyEmployeeException(Long id) {
        super(String.format(DEFAULT_MESSAGE, id));
    }
}
