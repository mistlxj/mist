package thinkingJava.generic.patameterizedType;

/**
 * Created by lixiaojian on 2017/8/10.
 */
public interface BaseInterface<Res,Param> {
    public Res invoke(Param p) throws Exception;
}
