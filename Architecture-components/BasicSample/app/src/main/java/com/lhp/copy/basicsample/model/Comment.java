package com.lhp.copy.basicsample.model;

import java.util.Date;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
public interface Comment {
    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();
}
