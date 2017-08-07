package com.jdkgroup.retrofitmvp2;

import com.jdkgroup.retrofitmvp2.model.MainCity;

/**
 * Created by kamlesh on 6/27/2017.
 */

public interface CountryPresenterListener{
    void countriesReady(MainCity mainCity);
    void appName(String appname);
}
