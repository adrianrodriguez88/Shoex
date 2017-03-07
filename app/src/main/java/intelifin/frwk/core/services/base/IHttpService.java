package intelifin.frwk.core.services.base;

import org.json.JSONObject;
import java.util.TreeMap;
import intelifin.frwk.core.services.exception.HttpServiceException;

/**
 * Created by AdrianRodriguez on 06/03/17.
 */

public interface IHttpService {

    public JSONObject post(String _endpoint, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException;
    public JSONObject get(String _endpoint, TreeMap<String, String> _headers) throws HttpServiceException;

}
