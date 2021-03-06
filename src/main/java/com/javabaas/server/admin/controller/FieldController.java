package com.javabaas.server.admin.controller;

import com.javabaas.server.admin.entity.Field;
import com.javabaas.server.admin.service.FieldService;
import com.javabaas.server.common.entity.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Staryet on 15/6/15.
 */
@RestController
@RequestMapping(value = "/api/master/clazz")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @RequestMapping(value = "/{clazzName}/field", method = RequestMethod.POST)
    public SimpleResult insert(@RequestHeader(value = "JB-AppId") String appId,
                               @PathVariable String clazzName,
                               @RequestBody Field field) {
        fieldService.insert(appId, clazzName, field);
        return SimpleResult.success();
    }

    @RequestMapping(value = "/{clazzName}/field/{name}", method = RequestMethod.DELETE)
    public SimpleResult delete(@RequestHeader(value = "JB-AppId") String appId,
                               @PathVariable String clazzName,
                               @PathVariable String name) {
        fieldService.delete(appId, clazzName, name);
        return SimpleResult.success();
    }

    @RequestMapping(value = "/{clazzName}/field/{name}", method = RequestMethod.PUT)
    public SimpleResult update(@RequestHeader(value = "JB-AppId") String appId,
                               @PathVariable String clazzName,
                               @PathVariable String name,
                               @RequestBody Field field) {
        fieldService.update(appId, clazzName, name, field);
        return SimpleResult.success();
    }

    @RequestMapping(value = "/{clazzName}/field/{name}/security", method = RequestMethod.PUT)
    public SimpleResult security(@RequestHeader(value = "JB-AppId") String appId,
                                 @PathVariable String clazzName,
                                 @PathVariable String name,
                                 @RequestParam boolean security) {
        fieldService.setSecurity(appId, clazzName, name, security);
        return SimpleResult.success();
    }

    @RequestMapping(value = "/{clazzName}/field/{name}/required", method = RequestMethod.PUT)
    public SimpleResult required(@RequestHeader(value = "JB-AppId") String appId,
                                 @PathVariable String clazzName,
                                 @PathVariable String name,
                                 @RequestParam boolean required) {
        fieldService.setRequired(appId, clazzName, name, required);
        return SimpleResult.success();
    }

    @RequestMapping(value = "/{clazzName}/field/{name}", method = RequestMethod.GET)
    public Field get(@RequestHeader(value = "JB-AppId") String appId,
                     @PathVariable String clazzName,
                     @PathVariable String name) {
        return fieldService.get(appId, clazzName, name);
    }

    @RequestMapping(value = "/{clazzName}/field", method = RequestMethod.GET)
    public List<Field> list(@RequestHeader(value = "JB-AppId") String appId,
                            @PathVariable String clazzName) {
        return fieldService.list(appId, clazzName);
    }

}
