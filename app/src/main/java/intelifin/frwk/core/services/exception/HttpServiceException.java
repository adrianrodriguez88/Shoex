package intelifin.frwk.core.services.exception;

/**
 * Created by AdrianRodriguez on 06/03/17.
 */

public class HttpServiceException extends Exception {

    private String _message = null;
    private int _code = -1;

    public HttpServiceException(String _message, int _code){
        this._message = _message;
        this._code = _code;
    }

    public String getMessage(){
        return _message;
    }

    public int getCode(){
        return _code;
    }
}
