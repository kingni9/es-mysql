主要功能：通过配置，将mysql数据导入elasticsearch（当前版本只兼容JaveBean字段名称和表字段名称相同的场景） <br>
当前版本：1.0.2 <br>
导入步骤:<br>
&nbsp;&nbsp;&nbsp;1、提供dataImport.xml配置文件，配置规范如下：<br>
```
    <!-- 索引名称 -->
    <index name="main">
        <!-- 类型名称 -->
        <type>pt_principal</type>
        <!-- 表名 -->
        <tableName>pt_principal</tableName>
        <!-- 数据导入sql -->
        <importSql>select * from pt_principal where principalId = 1</importSql>
    </index>
    ...
    <index name="xxx">
        <type>xxx</type>
        <tableName>xxx</tableName>
        <importSql>xxxx</importSql>
    </index>
```
&nbsp;&nbsp;&nbsp;2、修改application.properties相关ES节点配置、mysql数据源配置；<br>
&nbsp;&nbsp;&nbsp;3、直接运行EsApplicationTests#contextLoads();<br>
     
    
