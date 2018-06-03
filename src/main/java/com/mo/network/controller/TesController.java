package com.mo.network.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MoXingwang on 2017/6/14.
 */
@RestController
@RequestMapping(value = "test")
public class TesController {


    @RequestMapping(method = RequestMethod.GET)
    public String test() {

        return "";
    }
}
