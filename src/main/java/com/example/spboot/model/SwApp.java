package com.example.spboot.model;

public class SwApp {
    private String userNm;

    private String paraTypeCd;

    private String paraCd;

    private static final long serialVersionUID = 1L;

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm == null ? null : userNm.trim();
    }

    public String getParaTypeCd() {
        return paraTypeCd;
    }

    public void setParaTypeCd(String paraTypeCd) {
        this.paraTypeCd = paraTypeCd == null ? null : paraTypeCd.trim();
    }

    public String getParaCd() {
        return paraCd;
    }

    public void setParaCd(String paraCd) {
        this.paraCd = paraCd == null ? null : paraCd.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwApp)) return false;
        SwApp key = (SwApp) o;
        return key.paraCd.equals(paraCd)&&key.paraTypeCd.equals(paraTypeCd)&&key.paraCd.equals(paraCd);
    }
    @Override
    public int hashCode() {
        int result=17;
        result = result * 31 + userNm.hashCode();
        result = result * 31 +paraTypeCd.hashCode();
        result=result*31+paraCd.hashCode();
        return result;
    }
}
