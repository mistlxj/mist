package e.reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixiaojian on 2017/7/20.
 */
public class PrimitiveClass {
    private static Map<String,Object> tempMap = new HashMap<String, Object>();

    static {
        tempMap.put("id","3232");
        tempMap.put("name","张三");
    }

    private Long vehicleId = new Long(321L);
    private String vehicleName;
    private Long brandId;
    private String brandName;
    private Double sellPrice;


    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
