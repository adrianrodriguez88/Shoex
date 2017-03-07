package intelifin.frwk.core.services.core;

/**
 * Created by AdrianRodriguez on 06/03/17.
 */

public class ServicesManager {

    private static intelifin.frwk.core.services.core.ServicesManager _instance = null;

    private intelifin.frwk.core.services.base.IHttpService _httpService = null;


    public static intelifin.frwk.core.services.core.ServicesManager getInstance(){
        if (_instance == null)
            _instance = new intelifin.frwk.core.services.core.ServicesManager();
        return _instance;
    }

    private ServicesManager(){
        if (_httpService == null)
            _httpService = new intelifin.frwk.core.services.impl.HttpService();
    }

    public intelifin.frwk.core.services.base.IHttpService getHttpService() { return _httpService; }
}
