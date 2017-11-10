package thinkingJava.generic.patameterizedType;

import thinkingJava.generic.model.CountInteger;
import thinkingJava.generic.model.ResModel;

/**
 * Created by lixiaojian on 2017/8/10.
 */
public class Base implements BaseInterface<ResModel,CountInteger>{
    @Override
    public ResModel invoke(CountInteger p) throws Exception {
        return null;
    }
}
