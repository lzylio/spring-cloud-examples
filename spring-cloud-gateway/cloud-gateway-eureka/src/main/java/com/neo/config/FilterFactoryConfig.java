package com.neo.config;

import lombok.Data;

/**
 * 自定义的config类，用来设置传入的参数
 *
 * 例子：MyTestGatewayFilterFactory
 *   - name: MyTest
 *     args:
 *       enabled: true
 *       name: lio
 */
@Data
public class FilterFactoryConfig {

    // 控制是否开启认证
    public boolean enabled;

    // 姓名，用于验证传参
    public String name;

}
