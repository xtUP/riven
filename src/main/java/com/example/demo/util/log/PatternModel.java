package com.example.demo.util.log;

import java.io.Serializable;
import java.util.List;

/**
 * @Date 2020/7/18
 * @Author tiger
 */

public class PatternModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3565042765606630849L;

    private String name;

    private String regular;

    private boolean differInLen;

    public boolean isDifferInLen() {
        return differInLen;
    }

    public void setDifferInLen(boolean differInLen) {
        this.differInLen = differInLen;
    }

    private List<EncryptModel> encrypts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public List<EncryptModel> getEncrypts() {
        return encrypts;
    }

    public void setEncrypts(List<EncryptModel> encrypts) {
        this.encrypts = encrypts;
    }



}
