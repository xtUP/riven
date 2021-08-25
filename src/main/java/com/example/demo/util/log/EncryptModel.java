package com.example.demo.util.log;

import java.io.Serializable;

/**
 * @Date 2020/7/18
 * @Author tiger
 */

public class EncryptModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3012144038573177759L;

    private String length;

    private String start;

    private String end;



    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }





}
