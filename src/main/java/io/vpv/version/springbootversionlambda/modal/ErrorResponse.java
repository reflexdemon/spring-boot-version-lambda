package io.vpv.version.springbootversionlambda.modal;

/**
 * Created by vprasanna on 6/12/18.
 * The type Error response.
 */
public class ErrorResponse {

    private String code;
    private String message;

    /**
     * Instantiates a new Error response.
     *
     * @param code    the code
     * @param message the message
     */
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}
