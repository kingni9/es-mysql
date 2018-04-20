主要功能：通过配置，将mysql数据导入elasticsearch <br>
当前版本：1.0.1 <br>
导入步骤:<br>
&nbsp;&nbsp;&nbsp;1、提供importInfo.properties配置文件，配置规范如下：<br>
```
#tableName = import sql#
pt_principal=select * from pt_principal where principalId = 1
```
&nbsp;&nbsp;&nbsp;2、修改application.properties相关ES节点配置、mysql数据源配置；<br>
&nbsp;&nbsp;&nbsp;3、直接运行EsApplicationTests#contextLoads();<br>
     
    
