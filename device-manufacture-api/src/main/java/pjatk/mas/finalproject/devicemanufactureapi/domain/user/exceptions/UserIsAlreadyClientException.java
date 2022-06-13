package pjatk.mas.finalproject.devicemanufactureapi.domain.user.exceptions;

public class UserIsAlreadyClientException extends RuntimeException {


    private static final String DEFAULT_MESSAGE = "User id %s is already a client";

    public UserIsAlreadyClientException(Long id) {
        super(String.format(DEFAULT_MESSAGE,id));
    }
}
