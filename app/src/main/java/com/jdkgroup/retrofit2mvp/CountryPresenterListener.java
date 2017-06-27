package com.jdkgroup.retrofit2mvp;

import com.jdkgroup.retrofit2mvp.model.MainCity;

/**
 * Created by kamlesh on 6/27/2017.
 */

public interface CountryPresenterListener{
    void countriesReady(MainCity mainCity);
    void appName(String appname);
}
