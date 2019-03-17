package com.clsaa.dop.server.pipeline.controller;

import com.clsaa.dop.server.pipeline.config.BizCodes;
import com.clsaa.dop.server.pipeline.service.BlueOceanService;
import com.clsaa.dop.server.pipeline.service.JenkinsService;
import com.clsaa.rest.result.bizassert.BizAssert;
import com.clsaa.rest.result.bizassert.BizCode;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 流水线-jenkinsAPI接口实现类
 *
 * @author 张富利
 * @since 2019-03-15
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class JenkinsController {
    private String URI = "http://jenkins.dop.clsaa.com";
    private String User = "zfl";
    private String PWD = "zfl";
    private JenkinsService jenkinsService;

    private BlueOceanService blueOceanService = new BlueOceanService();


    @ApiOperation(value = "获取Authorization", notes = "获取Authorization, 供前端访问blueocean接口")
    @GetMapping("/v1/authorization")
    public String authorization() {
        return this.blueOceanService.getAuthorization();
    }

    @ApiOperation(value = "删除流水线", notes = "根据id删除流水线")
    @DeleteMapping("/v1/pipeline/byId")
    public void delete(String id) {
        BizAssert.validParam(StringUtils.isNotBlank(id),
                new BizCode(BizCodes.INVALID_PARAM.getCode(), "参数id非法"));
        try{
            jenkinsService = new JenkinsService(URI, User, PWD);
            this.jenkinsService.deleteJob(id);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
