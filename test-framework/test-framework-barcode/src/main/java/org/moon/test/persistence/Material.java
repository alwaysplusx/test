package org.moon.test.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    private String materialCode;
    private String materialName;
    private BigDecimal feedQuantity;
    private String station;
    private Date feedDate;
    private String hBox;
    
    public Material() {
    }

    public Material(String materialCode, String materialName) {
        this.materialCode = materialCode;
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getFeedQuantity() {
        return feedQuantity;
    }

    public void setFeedQuantity(BigDecimal feedQuantity) {
        this.feedQuantity = feedQuantity;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Date getFeedDate() {
        return feedDate;
    }

    public void setFeedDate(Date feedDate) {
        this.feedDate = feedDate;
    }

    public String gethBox() {
        return hBox;
    }

    public void sethBox(String hBox) {
        this.hBox = hBox;
    }

}
