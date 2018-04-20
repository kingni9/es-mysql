# es
>> 主要功能：通过配置，将mysql数据导入elasticsearch；
>> version:1.0.1
>> 导入步骤：
  >> 1、提供importInfo.properties配置文件，配置规范如下：
        #tableName = import sql#
        pt_principal=select * from pt_principal where principalId = 1
  >> 2、修改application.properties相关ES节点配置、mysql数据源配置；
  >> 3、直接运行EsApplicationTests#contextLoads(); #fileName参数可自定义#
  
